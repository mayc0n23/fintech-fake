FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG FINTECH_FAKE_EUREKA_SERVER
ARG KEYCLOAK_SERVER
ARG KEYCLOAK_PORT

ENTRYPOINT java -jar app.jar