# reactive-acl
A sample Java project to test ACL concept and utilize reactive REST services

## Run a docker container with MongoDb
docker run -d -p 27017-27019:27017-27019 --name acl-mongo mongo

## Cloud Config Server

### Config Server
A cloud-config server application URL is defined in application.yaml

Current value:
```
spring:
  config:
    import: optional:configserver:http://localhost:8899/
```

### Config server repo
Our config server is loaded configuration from the following repo:
https://github.com/grenader/cloud-config-repo.git

The config server has the following configuration:
```
# Github URL:
spring.cloud.config.server.git.uri=https://github.com/grenader/cloud-config-repo.git
```

### The application has several test URL to see configuration values coming from GitHub:
* http://localhost:8080/property/generic
* http://localhost:8080/property/application

### Refresh configuration
There is a way to refresh configuration properties loaded to the application. It's based on _Spring Actuator_
POST should be sent to the following URL: 
```
POST http://localhost:8080/actuator/refresh
``` 

The following section should be added to application.yaml file to enable 'refresh' end-point:
```
management:
  endpoints:
    web:
      exposure:
        include: "info,health,refresh"
```