#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/dropwizard-echo-message-1.0-SNAPSHOT.jar server config_remote.yml
