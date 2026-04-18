FROM maven:3.9.6-eclipse-temurin-17 as maven

WORKDIR /app

COPY . /app

RUN mvn package

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=maven /app/target/checkexam-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]