FROM openjdk:17-jdk-slim
ARG WAR_FILE=target/FoodTracker-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} application.war
ENTRYPOINT ["java", "-jar", "/application.war"]