# syntax=docker/dockerfile:1

# Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -DskipTests clean package

# Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
# Si tu JAR no termina en SNAPSHOT, cámbialo por el nombre exacto
COPY --from=build /app/target/*SNAPSHOT.jar app.jar

ENV PORT=8080
EXPOSE 8080
CMD ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
