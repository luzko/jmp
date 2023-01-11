FROM eclipse-temurin:17-jdk-alpine
RUN addgroup --system docker_group && adduser --system docker_user docker_group
USER docker_user
COPY /spring-petclinic/target/*.jar application.jar
CMD java $OPTIONS -jar application.jar
