#!/bin/bash 
java -Xms4G -Xmx4G -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/tomcat-echo-message-1.0-SNAPSHOT-jar-with-dependencies.jar
