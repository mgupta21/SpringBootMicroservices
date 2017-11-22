mvn clean package docker:build
docker-compose -f deploy/docker-compose.yml up