#!/bin/bash
CONSUMER_FOLDER_PATH="/home/magicmicky/IdeaProjects/TestProducer1/out/artifacts/TestProducer1_war_exploded";
GLASSFISH_DEPLOYER_PATH="/opt/glassfish4/bin/asadmin";

id=$1
exchange=$2
broadcast=$3
all=$4
echo "Using id $id, exc $exchange, broadcast $broadcast"
mkdir /tmp/webind
cd /tmp/webind
cp -R $CONSUMER_FOLDER_PATH ./producer-$id
echo $id > ./producer-$id/number.txt
echo $exchange >> ./producer-$id/number.txt
echo $broadcast >> ./producer-$id/number.txt
echo $all >> ./producer-$id/number.txt
${GLASSFISH_DEPLOYER_PATH} deploy ./producer-$id
cd
#rm -rf /tmp/webind
