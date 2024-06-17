FROM maven:3.8.8-openjdk-8-slim AS builder
WORKDIR /build
COPY . .
RUN mvn clean package
FROM tomcat:9.0.53-jdk8-openjdk-slim-buster
EXPOSE 3000
COPY --from=builder /build/target/enade.war /usr/local/tomcat/webapps/enade.war

ENTRYPOINT ["catalina.sh", "run"]
