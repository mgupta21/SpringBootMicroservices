# First start the eureka-server (port 8761)
mvn spring-boot:run
# Second start greetings-service (port 8762)
mvn spring-boot:run
# Third start greetings-client (port 8763)
mvn spring-boot:run
