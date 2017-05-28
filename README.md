# docker-demo
Small demo project to play around with docker

### Basic Components ###
* Spring Boot Application built with Gradle
* Simple Dockerfile that builds an image of alpine linux + java8 + runnable jar
* Docker compose file that will start 2 containers based on demo images (v1.1 and v1.2)

### Setup ###
* Import as a Gradle project
* run `gradle build buildDocker` to build a docker image (plugin starts using version tag 1.0)
   * You can add -Papp_version="X" to set the version tag

### Running in Docker ###
`docker run -d --name demo -p 8080:8080 docker-demo:1.0`  

`docker-compose up -d`

### Future plans ###
- [X] Convert project to Gradle
- [ ] Connect application to a redis data source
- [ ] Update REST service to use data source
- [ ] Expand on the compose file include a DB container
- [ ] Add Spring cloud config
- [ ] Secure REST API using Spring Security
