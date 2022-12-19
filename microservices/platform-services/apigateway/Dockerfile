FROM java:8-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/apigateway.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/apigateway.jar", "--spring.profiles.active=docker"]
EXPOSE 8099
