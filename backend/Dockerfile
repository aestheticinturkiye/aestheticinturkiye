FROM openjdk:20 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:20
WORKDIR backend
COPY --from=build target/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]