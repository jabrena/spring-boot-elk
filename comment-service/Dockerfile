FROM eclipse-temurin:17-jre-alpine

COPY ./target/comment-service-0.1.0-SNAPSHOT.jar comment-service.jar
ENTRYPOINT ["java", "-jar", "comment-service.jar","-Dspring.profiles.active=docker"]