#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/wso2msf4j-echo-message-1.0-SNAPSHOT.jar

