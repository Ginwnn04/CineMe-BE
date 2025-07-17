FROM maven:3.9.2-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY env.properties .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/CineMe-BE-0.0.1-SNAPSHOT.jar app.jar
COPY --from=builder /app/env.properties env.properties
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]