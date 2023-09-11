# Dockerfile para app1
FROM openjdk:17.0.2-slim

WORKDIR /app

COPY target/rinha-0.0.1.jar ./rinha-app.jar

ENV JAVA_OPTS="-XX:MaxRAM=430m -XX:MaxRAMFraction=4"

CMD ["java", "-jar", "rinha-app.jar"]
