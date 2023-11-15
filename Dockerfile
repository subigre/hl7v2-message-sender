FROM eclipse-temurin:17

COPY target/*.jar hl7v2-message-sender.jar

ENTRYPOINT ["java","-jar","/hl7v2-message-sender.jar"]