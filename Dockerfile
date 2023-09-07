FROM adoptopenjdk:17-jdk-hotspot

WORKDIR /app

COPY target/your-app.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]