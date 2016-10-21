#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/netty4-echo-message-0.0.1-SNAPSHOT.jar 192.36.157.174 8080
