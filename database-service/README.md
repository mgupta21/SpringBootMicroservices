mvn clean package docker:build
docker-compose -f deploy/docker-compose.yml up

# Notes 
- You shouldn't use your databases a synchronization layer, and you certainly shouldn't share data across different microservices, each talking to the same database. A database really needs to belong to a microservice.
- If you need to get data froma different microservice's database, you should call thatmicroservice and that should expose it through a REST call.

# Books
- Domain-Driven Design by Eric Evans
 
# Resource
- https://github.com/livelessons-spring/building-microservices

