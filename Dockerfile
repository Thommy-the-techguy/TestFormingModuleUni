#
# Build stage
#
FROM gradle:8.3.0-jdk20 AS build
COPY . .
RUN gradle clean build -x test

#
# Package stage
#
FROM openjdk:20-jdk-slim
COPY --from=build /build/libs/TestFormingModule-0.0.1-SNAPSHOT.war TestFormingModule-0.0.1-SNAPSHOT.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "TestFormingModule-0.0.1-SNAPSHOT.war"]