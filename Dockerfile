#
# Build stage
#
FROM gradle:8.3.0-jdk20 AS build
COPY . .
RUN ./gradlew build

#
# Package stage
#
FROM openjdk:20-jdk-slim
COPY --from=build /build/libs/TestFormingModule-0.0.1-SNAPSHOT.war app.war
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.war"]