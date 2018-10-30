FROM openjdk:8-jdk-alpine

WORKDIR SpringBootDocker/Git/SpringMicroServices-Docker/online-shoping/user-service

ADD target/user-service-0.0.1-SNAPSHOT.jar user-service-0.0.1-SNAPSHOT.jar

CMD exec java -jar user-service-0.0.1-SNAPSHOT.jar

EXPOSE 9098:9098
