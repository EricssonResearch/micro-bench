#!/bin/bash 
java -Xms4G -Xmx4G -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/wildfly-echo-message-1.0.0-SNAPSHOT-swarm.jar
