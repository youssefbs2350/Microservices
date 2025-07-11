# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built jar file into the container
COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar

# Expose the port
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "order-service.jar"]
