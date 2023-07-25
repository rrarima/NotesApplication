FROM eclipse-temurin:17

LABEL maintainer="rarimar4@gmail.com"

EXPOSE 8080

VOLUME /tmp

ADD build/libs/NotesApplication-0.0.1-SNAPSHOT.jar notes-application.jarr

ENTRYPOINT ["java","-jar","/notes-application.jar"]
