FROM eclipse-temurin:17-jre-alpine

VOLUME /tmp

COPY target/spring-boot-elk-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]