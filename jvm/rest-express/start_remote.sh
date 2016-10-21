#!/bin/bash 
java -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps -jar target/rest-express-echo-message-1.0-SNAPSHOT.jar -Dbase.uri=http://192.36.157.174:8080
