FROM java:8
ADD /target/Reporting-0.0.1-SNAPSHOT.jar Reporting.jar
ENTRYPOINT ["java","-jar","Reporting.jar"]