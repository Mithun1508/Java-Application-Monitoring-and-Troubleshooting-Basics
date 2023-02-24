FROM openjdk:11-jre-slim
WORKDIR /opt/agile-practices-application
ENTRYPOINT "./start-app.sh"
COPY dbo-1.0-SNAPSHOT.jar .
COPY start-app.sh .
RUN chmod a+x start-app.sh
