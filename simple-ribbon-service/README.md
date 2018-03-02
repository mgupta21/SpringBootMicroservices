# Source 
https://spring.io/guides/gs/client-side-load-balancing/

# Start 2 instances of say-hello service one at default port 8090 set in application.yml and another at 9999
mvn spring-boot:run
SERVER_PORT=9999 mvn spring-boot:run

# Start user service
mvn spring-boot:run

## Then start up the User service. Access localhost:8888/hi and then watch the Say Hello service instances. You can see Ribbon’s pings arriving every 15 seconds:
   
   2016-03-09 21:13:22.115  INFO 90046 --- [nio-8090-exec-1] hello.SayHelloApplication                : Access /
   2016-03-09 21:13:22.629  INFO 90046 --- [nio-8090-exec-3] hello.SayHelloApplication                : Access /
   
## And your requests to the User service should result in calls to Say Hello being spread across the running instances in round-robin form:
  
   2016-03-09 21:15:28.915  INFO 90046 --- [nio-8090-exec-7] hello.SayHelloApplication                : Access /greeting
   
## Now shut down a Say Hello server instance. Once Ribbon has pinged the down instance and considers it down, you should see requests begin to be balanced across the remaining instances.