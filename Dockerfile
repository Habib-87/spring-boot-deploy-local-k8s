FROM openjdk:11-jdk
VOLUME /tmp
COPY target/spring-boot-deploy-local-k8s-0.0.1-SNAPSHOT.jar app.jar
CMD [ "sh", "-c", "java -jar /app.jar" ]