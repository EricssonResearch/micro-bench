#!/bin/bash 
java -Xms4G -Xmx4G -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/vertx-echo-message-3.3.2-fat.jar -Dvertx.pool.worker.size=128

