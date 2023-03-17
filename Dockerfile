FROM openjdk:11-jdk-slim
MAINTAINER rangelsf
COPY target/*.jar prova.jar
ENTRYPOINT ["java", "-jar", "/prova.jar"]