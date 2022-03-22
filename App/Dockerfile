# Build stage
FROM maven:latest AS build
COPY src /app/src
COPY pom.xml /app
# need to assemble to package in plugins
RUN mvn -B -ntp --batch-mode -f /app/pom.xml clean compile assembly:single

# Run Stage
FROM openjdk:latest
WORKDIR /app

COPY --from=build /app/target/seMethods-1.0-jar-with-dependencies.jar build.jar
ENTRYPOINT ["java", "-jar", "build.jar"]
