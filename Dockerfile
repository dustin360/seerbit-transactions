FROM openjdk:8
VOLUME /tmp
ADD target/seerbit-transaction.jar app.jar
EXPOSE 9092
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]