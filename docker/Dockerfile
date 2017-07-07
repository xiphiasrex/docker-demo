FROM develar/java:8u45

VOLUME /tmp
ADD /maven/docker-demo-1.0.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]
