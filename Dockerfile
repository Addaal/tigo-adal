# Dockerfile

# Imagen base con Java 21
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copia el jar generado al contenedor
COPY build/libs/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
