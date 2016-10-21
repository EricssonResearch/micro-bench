#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/netty4-echo-message-0.0.1-SNAPSHOT-jar-with-dependencies.jar localhost 8080
