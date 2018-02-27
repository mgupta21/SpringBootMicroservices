FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd && apk add --update bash
RUN mkdir -p /usr/local/database-service
ADD @project.build.finalName@.jar /usr/local/database-service/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh