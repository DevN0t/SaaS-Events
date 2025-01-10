# First stage: Build the application
FROM openjdk:17-jdk-slim as build

RUN apt-get update && apt-get install -y maven

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn -Dmaven.test.skip=true package

# Second stage: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080

# Default command to run the jar file
ENTRYPOINT ["sh", "-c", "exec java ${JAVA_OPTS} -jar /app/app.jar"]
