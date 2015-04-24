#!/bin/sh


docker build -t microservices-mvn .

docker rm -f my-service-compil
docker run  \
    --name my-service-compil \
    -v ~/.m2:/root/.m2 \
    microservices-mvn install

docker run  -d \
    --volumes-from my-service-compil \
    --link eureka:eureka \
    --workdir="/project/my-service" \
    microservices-mvn spring-boot:run
