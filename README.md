# Description	

Code de l'article: http://scratch-your-own-it.ch/2015/04/microservices-springcloud-netflix-docker/

Ce projet est démontre la mise en œuvre de microservices dans une architecture java - spring. 


Les différentes versions montent en complexité et en fonctionnalités. 

L’application est composée de 2 modules:

1. L’application principale qui fait appel à 
2. Un service (greeter-service).

Pour passer d’une version à l’autre du code, il est nécessaire d’utiliser les tags.

# V1 : spring-boot-starter-web
Dans cette version, l’application et le service limitent leurs dépendances au maximum : 
spring-boot-starter-web.

Le service est défini grâce à l’annotation `@SpringBootApplication`. 
Le port est précisé dans le fichier `application.yml`.

L’application utilise RestTemplate pour effectuer son appel au service. 

**Inconvénients:**

L’application doit connaître précisément l’uri (dont le port) pour effectuer son appel. 

Le branchement de l’application directement sur un service en «dur» empêche la mise à jour de celui-ci. 

# V2 : CircuitBreaker pattern avec Netflix Hystrix 
Suppression des erreurs en cascade. Permet de changer un service sans arrêt total du système.

# V2.1 Utilisation de Feign pour les appels cliensts

# V3 Mise en œuvre du registre de services Eureka

# V4 Dockerisation
 