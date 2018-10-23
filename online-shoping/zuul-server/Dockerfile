FROM openjdk:8-jdk-alpine

WORKDIR SpringBootDocker/shopping-ws/online-shoping/zuul-server

ADD target/zuul-server-0.0.1-SNAPSHOT.jar zuul-server-0.0.1-SNAPSHOT.jar

CMD exec java -jar zuul-server-0.0.1-SNAPSHOT.jar

# EXPOSE 9090:9090
