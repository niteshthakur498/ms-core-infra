# Use a base image with Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/*.jar app.jar

RUN apt-get update && apt-get install -y curl

# Expose the port the Spring Boot application runs on
EXPOSE 7021

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
