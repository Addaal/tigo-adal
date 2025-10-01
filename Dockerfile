## Dockerfile
#
## Imagen base con Java 21
#FROM eclipse-temurin:21-jdk-alpine
#
## Directorio de trabajo en el contenedor
#WORKDIR /app
#
## Copia el jar generado al contenedor
#COPY build/libs/*.jar app.jar
#
## Expone el puerto 8080
#EXPOSE 8080
#
## Comando de arranque
#ENTRYPOINT ["java", "-jar", "app.jar"]
#
#

# Use official Java 21 slim image
FROM openjdk:21-jdk-slim

# Set environment variables
ENV APP_HOME=/app \
    PORT=8080

WORKDIR $APP_HOME

# Copy the built JAR into the container
COPY target/*.jar app.jar

# Expose the port Render will map
EXPOSE $PORT

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
