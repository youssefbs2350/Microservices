# Use a lightweight OpenJDK 17 image as base
FROM eclipse-temurin:17-jdk-alpine

# Set a working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
# Make sure to build your project first with: ./mvnw clean package (or mvn clean package)
COPY product-service-0.0.1-SNAPSHOT.jar product-service.jar


# Expose the port your app will run on
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "product-service.jar"]
