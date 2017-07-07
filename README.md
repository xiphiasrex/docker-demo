# docker-demo
Small demo project to play around with docker and redis

### Basic Components ###
* Spring Boot Application built with maven
* Simple Dockerfile that builds an image of alpine linux + java8 + runnable jar
* Docker compose file that will start a container for the app and one for a redis server

### Setup ###
* Import as a Maven project
* run `mvn package docker:build` to build a docker image (plugin starts using version tag 1.0)

### Running in Docker ###
`docker-compose up -d`
* Need to be in `[project]/docker` folder  
* The volume `/docker/host/dir` needs to be available to docker
* Can access the application from <http://localhost:8080/docker-demo/swagger-ui.html>

#### Alternate startup without compose ####
1. `docker network create -d bridge net-docker-demo`  
   * Creates a simple user defined network for our docker containers
   
2. `docker run --name redis --network net-docker-demo -v /docker/host/dir:/data redis redis-server --appendonly yes`  
   * Starts a redis server in docker on the user network  
   * The volume `/docker/host/dir` needs to be available to docker, or you can use a different directory

3. `docker run --name docker-demo --network net-docker-demo  -p 8080:8080 docker-demo:1.0`  
   * Starts the app container on the same network so it can access redis
   
#### Connect to redis server CLI ####
`docker run -it --network net-docker-demo --rm redis redis-cli -h redis -p 6379`  
* Optional additional command that starts a redis CLI session

### Future plans ###
- [X] Convert project to Gradle
- [X] ...and back to Maven
- [X] Connect application to a redis data source
- [X] Update REST service to use data source
- [X] Expand on the compose file include a DB container
- [ ] Add Spring cloud config
