#!/bin/sh


docker build -t microservices-mvn .

docker rm -f my-service-compil
docker run  \
    --name my-service-compil \
    -v ~/.m2:/root/.m2 \
    microservices-mvn install

docker rm -f my-app
docker run  -d \
    --name my-app \
    --volumes-from my-service-compil \
    --link eureka:eureka \
    --workdir="/project/my-app" \
    -p 8082:8082 \
    microservices-mvn spring-boot:run
