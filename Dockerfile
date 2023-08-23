FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=build /libs/bucard.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080