package lt.dvim.sssio

import java.io.{File, PipedInputStream, PipedOutputStream}
import java.net.URI
import java.nio.channels.Channels
import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.unixdomainsocket.scaladsl.UnixDomainSocket
import akka.stream.scaladsl.{Flow, StreamConverters}
import io.circe._
import io.circe.parser._
import io.circe.generic.auto._
import jnr.unixsocket.{UnixSocketAddress}

import scala.io.Source
import scala.util.Try

object Sssio {
  case class SbtServer(uri: URI)

  implicit val decodeUri: Decoder[URI] = c =>
    for {
      uri <- c.as[String]
    } yield {
      new URI(uri)
  }

  def main(args: Array[String]): Unit = {
    val address = for {
      json <- Try(Source.fromFile(Paths.get("project", "target", "active.json").toFile).mkString).toEither
      sbtServer <- decode[SbtServer](json)
      address <- sbtServer.uri.getScheme match {
        case "local" => Right(new UnixSocketAddress(sbtServer.uri.getPath))
        case other => Left(new IllegalArgumentException(s"Unsupported scheme [$other]"))
      }
    } yield address

    address match {
      case Left(ex) => println(ex.getMessage)
      case Right(address) =>
        implicit val sys = ActorSystem("sssio")
        implicit val mat = ActorMaterializer()

        Flow
          .fromSinkAndSource(
            StreamConverters.fromOutputStream(() => System.out),
            StreamConverters.fromInputStream(() => System.in)
          )
          .join(UnixDomainSocket().outgoingConnection(address))
          .run()
    }
  }

}
