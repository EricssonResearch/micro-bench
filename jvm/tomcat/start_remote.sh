#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/tomcat-echo-message-1.0-SNAPSHOT.jar
