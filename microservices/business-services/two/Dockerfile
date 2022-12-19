FROM java:8-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/service-two.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/service-two.jar", "--spring.profiles.active=docker"]
EXPOSE 8092
