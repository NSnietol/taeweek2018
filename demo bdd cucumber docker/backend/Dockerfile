FROM basedemo:latest

WORKDIR /opt/docker-todo/backend

RUN ./mvnw install 

EXPOSE 8081
ENTRYPOINT ["/usr/bin/java", "-jar", "/opt/docker-todo/backend/target/todo-backend.jar"]
