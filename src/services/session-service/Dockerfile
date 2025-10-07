# Stage 1 — Build dependencies
FROM maven:3.9.4-eclipse-temurin-17-alpine AS dependencies

WORKDIR /app

# Копируем только pom.xml и maven-wrapper для скачивания зависимостей
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

# Загружаем зависимости в offline-режим (кэшируется)
RUN chmod +x mvnw && mvn dependency:go-offline

# Stage 2 — Build project
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY --from=dependencies /root/.m2 /root/.m2
COPY . .

RUN mvn package -DskipTests

# Stage 3 — Run
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
#wait-for-it.sh
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]