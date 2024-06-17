FROM maven:3.8.1-jdk-8 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package
FROM tomcat:9.0.53-jdk8-openjdk-slim-buster
EXPOSE 8080
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/enade.war
CMD ["catalina.sh", "run"]
