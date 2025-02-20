# Stage 1: Use Maven to build the project
FROM maven:3.8.1-openjdk-17 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application (this creates the target/*.jar file)
RUN mvn clean package -DskipTests -Dmaven.compiler.release=17


# Use an OpenJDK 23 base image
FROM openjdk:17
# Set the working directory inside the container
WORKDIR /app
# Copy the jar file from your machine to the container
COPY --from=builder /app/target/*.jar app.jar
# Expose the port your application will run on
EXPOSE 8080
# Run the jar file
CMD ["java", "-jar", "app.jar"]
