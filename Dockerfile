# Etapa 1: Construção da aplicação
FROM maven:3.8.8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa 2: Configuração do Tomcat
FROM tomcat:9.0.53-jdk8-openjdk-slim-buster
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]
