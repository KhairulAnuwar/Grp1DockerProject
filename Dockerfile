# FROM openjdk:17-jdk
# COPY target/*.jar app.jar
# EXPOSE 9090
# ENTRYPOINT ["java", "-jar", "/app.jar"]

# FROM openjdk:17-jdk-slim
# WORKDIR /app
# COPY target/*.jar app.jar
# EXPOSE 9090
# ENTRYPOINT ["java", "-jar", "app.jar"]


# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-Dserver.port=${PORT:9090}", "-jar", "app.jar"]
