FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/*.jar"]