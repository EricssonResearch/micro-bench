#!/bin/bash 
java -Xms4G -Xmx4G -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/spark-echo-message-1.0-SNAPSHOT.jar 127.0.0.1
