FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:11-jdk-slim

WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/charybdis-demo.jar /app/
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Ddatastax-java-driver.basic.contact-points.0=charybdis-demo-scylla:9042","-Ddatastax-java-driver.basic.load-balancing-policy.local-datacenter=datacenter1","-jar","charybdis-demo.jar"]

