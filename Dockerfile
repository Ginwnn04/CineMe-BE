FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/CineMe-BE-0.0.1-SNAPSHOT.jar app.jar
COPY env.properties env.properties
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]