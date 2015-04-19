# Description	

Ce projet démontre la mise en œuvre de microservices dans une architecture java - spring. 

Les différentes versions montent en complexité et en fonctionnalités. 

L’application est composée de 2 modules:

1. L’application principale qui fait appel à 
2. Un service (greeter-service).

Pour passer d’une version à l’autre du code, il est nécessaire d’utiliser les tags.

# Version 1 : spring-boot-starter-web
Dans cette version, l’application et le service limitent leurs dépendances au maximum : 
spring-boot-starter-web.

Le service est défini grâce à l’annotation `@SpringBootApplication`. 
Le port est précisé dans le fichier `application.yml`.

L’application utilise RestTemplate pour effectuer son appel au service. Elle doit connaître précisément 
l’uri (dont le port) pour effectuer son appel. 

 