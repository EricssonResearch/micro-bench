#!/bin/bash 
java -Dninja.jetty.configuration=jetty.xml -Dninja.mode=prod -Dninja.ssl.port=-1 -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/ninjaframework-echo-message-1.0-SNAPSHOT.jar