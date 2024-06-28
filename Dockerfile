FROM openjdk:21
EXPOSE 8080
WORKDIR /app

COPY target/sec-3-0.0.1-SNAPSHOT.jar /app/sec-3-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar","sec-3-0.0.1-SNAPSHOT.jar"]

