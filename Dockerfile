FROM gradle:8.2.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jdk-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/bucard.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080