# Build stage
FROM maven:latest AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

# Package stage
FROM openjdk:latest
COPY --from=build /app/target/seMethods-1.0.jar /usr/local/lib/build.jar
ENTRYPOINT ["java", "-cp", "/usr/local/lib/build.jar", "com.napier.sem.App"]
