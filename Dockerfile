# FROM openjdk:17-jdk
# COPY target/*.jar app.jar
# EXPOSE 9090
# ENTRYPOINT ["java", "-jar", "/app.jar"]

# FROM openjdk:17-jdk-slim
# WORKDIR /app
# COPY target/*.jar app.jar
# EXPOSE 9090
# ENTRYPOINT ["java", "-jar", "app.jar"]

#--------------------------------------------------------------------------

# # Build stage
# FROM maven:3.8.4-openjdk-17 AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean package -DskipTests

# # Run stage
# FROM openjdk:17-jdk-slim
# WORKDIR /app
# COPY --from=build /app/target/*.jar app.jar
# EXPOSE 8080
# # ENTRYPOINT ["java", "-jar", "app.jar"]

# # CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]
# CMD ["java", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]


# -----------------------------------------------------------------------------------

# --bindu-----------
#Use Eclipse Temurin JDK for Java 17

FROM eclipse-temurin:17-jdk-jammy

#set the working dir

WORKDIR /app

#Copy the Maven wrapper and POM files

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

#Download the dependencies

RUN ./mvnw dependency:resolve

#Copy application source code

COPY src ./src

#Build the application and run tests

RUN ./mvnw package -DskipTests

#Default command to start the Spring Boot Application

CMD ["./mvnw","spring-boot:run"]
