#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/springboot-echo-message-1.0-SNAPSHOT.jar
