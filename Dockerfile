FROM java:8
EXPOSE 8080
ADD /target/reporting-1.0-SNAPSHOT.jar reporting.jar
ENTRYPOINT ["java","-jar","reporting.jar"]