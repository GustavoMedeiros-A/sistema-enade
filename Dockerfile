# Step 1: Build the Java application using Maven
FROM maven:3.8.1-jdk-8 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the application source code and build the application
COPY src ./src
RUN mvn clean package

# Step 2: Prepare the Tomcat server
FROM tomcat:9.0.53-jdk8-openjdk-slim-buster

# Expose the default Tomcat port
EXPOSE 8080

# Copy the WAR file from the build stage
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/enade.war

# Start the Tomcat server
CMD ["catalina.sh", "run"]
