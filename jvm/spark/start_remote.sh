#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/spark-echo-message-1.0-SNAPSHOT.jar 192.36.157.174
