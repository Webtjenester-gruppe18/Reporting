FROM java:8
EXPOSE 8080
ADD /target/Reporting-1.0-SNAPSHOT.jar Reporting.jar
ENTRYPOINT ["java","-jar","Reporting.jar"]