FROM openjdk:17-jdk
COPY target/ftbsolutionscrm-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.jar"]