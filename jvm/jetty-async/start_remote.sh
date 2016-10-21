#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/jetty-async-echo-message-1.0-SNAPSHOT-jar-with-dependencies.jar 0.0.0.0 8080
