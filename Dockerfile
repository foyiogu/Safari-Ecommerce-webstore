FROM openjdk:16-alpine as build
ADD target/Safari-Web-Store.jar Safari-Web-Store.jar
EXPOSE 80
ENTRYPOINT [ "java", "-jar", "Safari-Web-Store.jar"]
