#!/bin/bash
java -Xms4G -Xmx4G -Dninja.jetty.configuration=jetty.xml -Dninja.mode=prd -Dninja.ssl.port=-1 -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/ninjaframework-echo-message-1.0-SNAPSHOT.jar
