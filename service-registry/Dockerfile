FROM maven:3.9.5-eclipse-temurin-21 as builder
COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=builder target/*.jar app.jar
CMD ["java","-jar","app.jar"]