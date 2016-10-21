#!/bin/bash
export SBT_OPTS="-Xms6G -Xmx6G -Xloggc:$1 -verbose:gc -XX:+PrintGCDateStamps"
sbt run
