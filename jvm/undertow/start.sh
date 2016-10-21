#!/bin/bash
java -Xms4G -Xmx4G -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/undertow-echo-message-1.0-SNAPSHOT-jar-with-dependencies.jar 127.0.0.1
