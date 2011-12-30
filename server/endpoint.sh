#!/bin/bash

d_start() {
    java -jar com.hoegernet.dynamicdns.server_1.0.0.jar start &
}

d_stop() {
    java -jar com.hoegernet.dynamicdns.server_1.0.0.jar stop
}

d_config() {
    java -jar com.hoegernet.dynamicdns.server_1.0.0.jar config
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
