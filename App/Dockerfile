# Build stage
FROM maven:latest AS build
COPY src /app/src
COPY pom.xml /app
# need to assemble to package in plugins
RUN mvn -B -ntp --batch-mode -f /app/pom.xml clean compile assembly:single

# Package stage
FROM openjdk:latest
COPY --from=build /app/target/seMethods-1.0-jar-with-dependencies.jar /usr/local/lib/build.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/build.jar"]
