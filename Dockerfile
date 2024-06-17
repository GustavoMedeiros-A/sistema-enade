FROM tomcat:9.0.53-jdk8-openjdk-slim-buster

EXPOSE 8080
ENV MAVEN_VERSION=3.8.8

RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    curl -fsSL -o /tmp/apache-maven.tar.gz https://downloads.apache.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    tar -xzf /tmp/apache-maven.tar.gz -C /usr/share && \
    ln -s /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven && \
    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn && \
    rm -f /tmp/apache-maven.tar.gz && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get autoremove -y && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* 

WORKDIR /Java

COPY . .
RUN mvn clean package 
RUN cp ./target/enade.war /usr/local/tomcat/webapps/enade.war

CMD ["catalina.sh", "run"]
