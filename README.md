# docker-demo
Small demo project to play around with docker

### Basic Components ###
* Spring Boot Application built with Maven
* Simple Dockerfile that builds an image of alpine linux + java8 + runnable jar
* Docker compose file that will start 2 containers based on demo images (v1.1 and v1.2)

### Setup ###
* Import as a normal maven project
* run `mvn package docker:build` to build a docker image (plugin starts using version tag 1.1)

### Running in Docker ###
`docker run -d --name demo -p 8080:8080 docker-demo:1.1`  

`docker-compose up -d`
* Compose file assumes there is also a v1.2 tagged image:

### Future plans ###
- [ ] Connect REST service to a DB
- [ ] Expand on the compose file include a DB container
- [ ] Secure REST API using Spring Security
