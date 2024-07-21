FROM --platform=linux/amd64 openjdk:21-jdk AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM --platform=linux/amd64 openjdk:21-jdk
WORKDIR backend
COPY --from=build target/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
