# FROM openjdk:17-jdk
# COPY target/*.jar app.jar
# EXPOSE 9090
# ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]