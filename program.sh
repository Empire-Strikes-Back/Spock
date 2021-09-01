#!/bin/bash

repl(){
  clj \
    -J-Dclojure.core.async.pool-size=1 \
    -X:repl ripley.core/process \
    :main-ns spock.main
}

main(){
  clojure \
    -J-Dclojure.core.async.pool-size=1 \
    -M -m spock.main
}

uberjar(){

  clojure \
    -X:uberjar genie.core/process \
    :main-ns spock.main \
    :filename '"out/spock.jar"' \
    :paths '["src" "out/identicon"]'
}

release(){
  uberjar
}

"$@"