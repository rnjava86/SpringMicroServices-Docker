FROM openjdk:8-jdk-alpine

WORKDIR SpringBootDocker/shopping-ws/online-shoping/eureka-server

ADD target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server-0.0.1-SNAPSHOT.jar

CMD exec java -jar eureka-server-0.0.1-SNAPSHOT.jar

# EXPOSE 8761:8761
