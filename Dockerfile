# Stage 1: Build
FROM maven:3.9.2-eclipse-temurin-17 AS builder
WORKDIR /app
COPY backend/.mvn backend/.mvn
COPY backend/mvnw backend/mvnw
COPY backend/pom.xml backend/
COPY backend/src backend/src
RUN chmod +x backend/mvnw
RUN cd backend && ./mvnw clean install -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/backend/target/ecommerce-backend-1.0.0.jar app.jar
ENV JAVA_OPTS="-Xms256m -Xmx512m"
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
