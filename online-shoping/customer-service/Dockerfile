FROM openjdk:8-jdk-alpine

WORKDIR SpringBootDocker/shopping-ws/online-shoping/customer-service

ADD target/customer-service-0.0.1-SNAPSHOT.jar customer-service-0.0.1-SNAPSHOT.jar

CMD exec java -jar customer-service-0.0.1-SNAPSHOT.jar

# EXPOSE 9098:9098
