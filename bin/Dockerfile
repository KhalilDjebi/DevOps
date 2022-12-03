FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD ./target/achat-1.1.0.jar Test_Prod.jar 
ENTRYPOINT ["java","-jar","/Test_Prod.jar"]