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
* Access Swagger UI via <http://localhost:8080/docker-demo/swagger-ui.html>

`docker-compose up -d`
* Access Swagger UI via <http://localhost:8080/docker-demo/swagger-ui.html> or <http://localhost:8081/docker-demo/swagger-ui.html>

#### Slightly more involved setup with a app container and redis db ####
1. `docker network create -d bridge net-main`  
   * Creates a simple user defined network for our docker containers
   
2. `docker run --name redis --network net-main -v /docker/host/dir:/data redis redis-server --appendonly yes`  
   * Starts a redis server in docker on the user network

3. `docker run --name docker-demo --network net-main  -p 8080:8080 docker-demo:1.0`  
   * Starts the app container on the same network so it can access redis
   
4. `docker run -it --network net-main --rm redis redis-cli -h redis -p 6379`  
   * Optional additional command that starts a redis CLI session

### Future plans ###
- [X] Convert project to Gradle
- [X] Connect application to a redis data source
- [X] Update REST service to use data source
- [ ] Expand on the compose file include a DB container
- [ ] Add Spring cloud config
- [ ] Secure REST API using Spring Security
