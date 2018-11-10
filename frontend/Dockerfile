FROM basedemo:latest

WORKDIR /opt/docker-todo/frontend

RUN ./mvnw install
	
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "/opt/docker-todo/frontend/target/todo-frontend.jar"]
