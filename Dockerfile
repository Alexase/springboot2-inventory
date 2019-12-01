FROM openjdk:8
EXPOSE 8080
ADD target/springboot2-inventory.jar springboot2-inventory.jar
ENTRYPOINT ["java","-jar","/springboot2-inventory.jar"]