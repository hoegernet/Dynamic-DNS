#!/bin/bash

case "$1" in
start)
    java -jar com.hoegernet.dynamicdns.client_@VERSION@.jar start &
    ;;
stop)
    java -jar com.hoegernet.dynamicdns.client_@VERSION@.jar stop
    ;;
config)
    java -jar com.hoegernet.dynamicdns.client_@VERSION@.jar config
    ;;
restart)
    $0 stop
    sleep 4
    $0 start
    ;;
*)
    echo "usage: $0 (start|stop|restart|config|help)"
esac
