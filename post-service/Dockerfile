FROM eclipse-temurin:17-jre-alpine

COPY ./target/post-service-0.1.0-SNAPSHOT.jar post-service.jar
ENTRYPOINT ["java", "-jar", "post-service.jar","-Dspring.profiles.active=docker"]
