#!/bin/bash

d_start() {
    java -jar com.hoegernet.dynamicdns.server_@VERSION@.jar start &
}

d_stop() {
    java -jar com.hoegernet.dynamicdns.server_@VERSION@.jar stop
}

d_config() {
    java -jar com.hoegernet.dynamicdns.server_@VERSION@.jar config
}

d_log() {
    tail -f /var/log/ddns.log
}


case "$1" in
    start)
        d_start
        ;;
    stop)
        d_stop
        ;;
    config)
        d_config
        ;;
    log)
        d_log
        ;;
    *)
        echo "commands are $0 start|stop|config|log"
esac
