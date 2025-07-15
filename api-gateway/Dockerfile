# Use official OpenJDK image as base
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

# Expose the application port (adjust if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "api-gateway.jar", "--spring.profiles.active=docker"]
