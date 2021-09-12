#!/bin/bash

repl(){
  clj \
    -J-Dclojure.core.async.pool-size=1 \
    -X:repl Ripley.core/process \
    :main-ns Spock.main
}

main(){
  clojure \
    -J-Dclojure.core.async.pool-size=1 \
    -M -m Spock.main
}

uberjar(){

  clojure \
    -X:identicon Zazu.core/process \
    :word '"Spock"' \
    :filename '"out/identicon/icon.png"' \
    :size 256

  clojure \
    -X:uberjar Genie.core/process \
    :main-ns Spock.main \
    :filename '"out/Spock.jar"' \
    :paths '["src" "out/identicon"]'
}

release(){
  uberjar
}

"$@"