FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.jar Orders.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","Orders.jar"]