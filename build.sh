#!/bin/bash
cd pushnotifier-producer-api
mvn clean install
cd ..
cd pushnotifier-producer-spring
mvn clean package
