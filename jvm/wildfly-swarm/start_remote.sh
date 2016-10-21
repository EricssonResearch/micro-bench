#!/bin/bash 
java -Xloggc:$1x -verbose:gc -XX:+PrintGCDateStamps -jar target/wildfly-echo-message-1.0.0-SNAPSHOT-swarm.jar
