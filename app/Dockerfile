FROM gradle:8.5.0-alpine as builder
WORKDIR /app
COPY . .
RUN ./gradlew clean shadowJar

FROM eclipse-temurin:20-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 7070
ENTRYPOINT ["java", "-jar", "app.jar"]