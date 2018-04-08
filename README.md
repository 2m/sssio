# [sssio][] [![scaladex-badge][]][scaladex] [![travis-badge][]][travis] [![gitter-badge][]][gitter]

[sssio]:              https://github.com/2m/sssio
[scaladex]:           https://index.scala-lang.org/2m/sssio
[scaladex-badge]:     https://index.scala-lang.org/2m/sssio/latest.svg
[travis]:             https://travis-ci.org/2m/sssio
[travis-badge]:       https://travis-ci.org/2m/sssio.svg?branch=master
[gitter]:             https://gitter.im/2m/sssio
[gitter-badge]:       https://badges.gitter.im/2m/sssio.svg

[sbt server](https://www.scala-sbt.org/1.x/docs/sbt-server.html) talks Language Server Protocol using Unix domain sockets. `sssio` (**s**bt **s**erver **s**tandard **i**nput and **o**utput) automatically discovers sbt server's opened socket and converts it to standard input and output which most of the editors expect.

## Setup

If you are using neovim with [autozimu/LanguageClient-neovim](https://github.com/autozimu/LanguageClient-neovim) then add the following to your `~/.config/nvim/init.vim`:

```vim
let g:LanguageClient_serverCommands = {
    \ 'scala': ['coursier', 'launch', 'lt.dvim.sssio:sssio_2.12:0.0.1'],
    \ }
```

This uses [coursier](https://github.com/coursier/coursier) to fetch the artifacts. If you are running Archlinux you can install `coursier` from [AUR](https://aur.archlinux.org/packages/coursier/).

## Config

To write all of the communication between sbt and LSP client to a file, add the following parameter:

```
-Dsssio.logfile=target/sssio.log
```

## Licence

Copyright 2018 https://github.com/2m/sssio/graphs/contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
