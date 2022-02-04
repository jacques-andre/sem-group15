FROM openjdk:latest
# build maven
CMD ["mvn","compile"]

# copy build from target 
COPY ./target/classes/com /tmp/com
WORKDIR /tmp
ENTRYPOINT ["java", "com.napier.sem.App"]
