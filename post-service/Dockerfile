FROM eclipse-temurin:17.0.6_10-jre-alpine
EXPOSE 8001
ARG OTEL_JAVA_AGENT_VERSION=1.24.0
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_JAVA_AGENT_VERSION}/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-javaagent:/opentelemetry-javaagent.jar","-jar","/application.jar"]