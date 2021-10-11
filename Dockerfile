ARG dockerRepository
FROM ${dockerRepository}openjdk:8-slim
ARG JAR_FILE=target/*.jar
RUN mkdir /app
COPY ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]
CMD [ "--spring.profiles.active=dsv" ]