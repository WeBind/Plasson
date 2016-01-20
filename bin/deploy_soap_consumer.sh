#!/bin/bash

CONSUMER_JAR=/home/magicmicky/LinuxData/Cours/5A/pdl/SoapJavaConsumer/classes/artifacts/SoapJavaConsumer_jar/SoapJavaConsumer.jar

id=$1
exchange=$2
broadcast=$3
all=$4

java -jar ${CONSUMER_JAR} $îd $exchange $broadcast $all
