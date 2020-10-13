FROM openjdk:8-jdk-alpine

WORKDIR /app

RUN mkdir -p /root/data/in
RUN mkdir -p /root/data/out

COPY target/processador-analise-vendas-1.0.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]