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

FROM gradle:8.7-jdk21 AS builder
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

RUN ./gradlew dependencies --no-daemon || return 0
COPY src ./src
RUN ./gradlew bootJar --no-daemon


FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENV PORT=8080
EXPOSE $PORT
ENTRYPOINT ["java","-jar","app.jar"]
