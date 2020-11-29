###FROM openjdk:8-jdk-alpine
FROM adoptopenjdk/openjdk11:alpine

RUN apk --no-cache add curl

#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

VOLUME /tmp

ARG REGION_ARG=sa-east-1
ARG ACCESS_ARG
ARG SECRET_ARG

ENV AWS_REGION=$REGION_ARG
ENV AWS_ACCESS_KEY=$ACCESS_ARG
ENV AWS_SECRET_KEY=$SECRET_ARG

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]