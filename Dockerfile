FROM openjdk:25-slim-bullseye
MAINTAINER "Sandeep"
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]