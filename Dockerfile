FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/algoviz-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
