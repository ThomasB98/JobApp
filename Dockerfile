FROM ubuntu:latest
LABEL authors="thoma"
ARG JAR_FILE=target/*.jar
COPY ./terget/jonapp-0.0.1-SNAPSHOT.jar aap.jar
ENTRYPOINT ["java","-jar","/app.jar"]