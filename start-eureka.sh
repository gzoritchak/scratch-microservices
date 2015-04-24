#!/bin/sh

docker build -t eureka-mvn .

docker rm -f eureka-compil
docker run  \
    --name eureka-compil \
    -v ~/.m2:/root/.m2 \
    eureka-mvn install

docker rm -f eureka
docker run  -d \
    --name eureka \
    --volumes-from eureka-compil \
    --workdir="/project/eureka" \
    -p 8761:8761 \
    eureka-mvn spring-boot:run
