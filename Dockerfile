FROM amazoncorretto:11-alpine3.14

COPY target/user-service-backend.jar /var/lib/service.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "/var/lib/service.jar"]