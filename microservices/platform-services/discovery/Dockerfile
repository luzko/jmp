FROM java:8-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/discovery.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/discovery.jar", "--spring.profiles.active=docker"]
EXPOSE 8761
