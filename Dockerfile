FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação para o contêiner
COPY target/rinha.jar app.jar

# Exponha a porta em que sua aplicação Spring Boot está configurada para ouvir (por padrão, 8080)
EXPOSE 8080

# Comando para executar sua aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]