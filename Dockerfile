#
# Build stage
#
FROM gradle:8.3.0-jdk20 AS cache
#COPY . .
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle /home/gradle/java-code/
WORKDIR /home/gradle/java-code
RUN gradle clean build -i --stacktrace

FROM gradle:8.3.0-jdk20 AS builder
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY . /usr/src/java-code/
COPY .env /usr/src/java-code/
WORKDIR /usr/src/java-code
RUN gradle bootWar -i --stacktrace

#
# Package stage
#
FROM openjdk:20-jdk-slim
#COPY --from=build /build/libs/TestFormingModule-0.0.1-SNAPSHOT.war app.war
# ENV PORT=8080
USER root
WORKDIR /usr/src/java-app
COPY --from=builder /usr/src/java-code/build/libs/TestFormingModule-0.0.1-SNAPSHOT.war ./app.war
ENTRYPOINT ["java", "-jar", "app.war"]