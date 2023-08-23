FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/bucard.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080