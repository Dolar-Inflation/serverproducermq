# Этап сборки
FROM bellsoft/liberica-openjdk-debian:17 AS build
WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY . .
RUN mvn clean package -DskipTests

RUN mvn clean package -DskipTests

# Этап запуска
FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]