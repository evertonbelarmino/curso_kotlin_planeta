FROM openjdk:8-jdk-alpine
MAINTAINER evertonbelarmino
WORKDIR /home/cadmus/Documentos/Outros/app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
