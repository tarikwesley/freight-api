FROM openjdk:11-jre-slim

RUN apt update && apt install bash wget -y

RUN mkdir -p /opt/app

ENV APP_HOME /opt/app

COPY build/libs/freight-0.0.1-SNAPSHOT.jar $APP_HOME/freight-0.0.1-SNAPSHOT.jar

WORKDIR $APP_HOME

CMD ["java", "-jar", "-Dspring.profiles.active=prod" ,"./freight-0.0.1-SNAPSHOT.jar"]