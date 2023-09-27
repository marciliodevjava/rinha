# Dockerfile para app1
FROM openjdk:17-alpine

WORKDIR /app

COPY target/rinha-0.0.1.jar ./rinha-app.jar

ENV JAVA_OPTS="-XX:MinRAM=396m -XX:MaxRAMFraction=3"

CMD ["java", "-jar", "rinha-app.jar"]
