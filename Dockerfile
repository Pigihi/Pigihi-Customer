FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY gradle/ gradle
COPY gradlew build.gradle ./
COPY src ./src

RUN ./gradlew build

EXPOSE 8080

CMD ["./gradlew", "bootRun"]
