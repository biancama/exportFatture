#!/usr/bin/env bash
PROJECT_FOLDER=/Users/massimo.biancalani/git_repo/meo/exportFatture
PROPERTIES_FILE=/Users/massimo.biancalani/git_repo/meo/exportFatture/application.yml
FROM_DATE=`date -v -1d +%d-%m-%Y`
TO_DATE=`date +%d-%m-%Y`
java -jar ${PROJECT_FOLDER}/target/exportFatture-0.0.1-SNAPSHOT.jar ${FROM_DATE} ${TO_DATE} --spring.config.location=file://${PROPERTIES_FILE}