build:
	mvn install -DskipTests -f pom.xml

image:
	docker build -t api-docker.jar .

run:
	docker-compose up -d

test:
	mvn test -f pom.xml

stop:
	docker-compose down

.PHONY: build image run test stop
