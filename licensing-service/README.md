# Run Instructions 
docker-compose -f docker/common/docker-compose.yml up

# Principles of Application Configuration management
1. Segregate: Application configuration should not be deployed with the service instance. Instead, configuration information should either be passed to the starting service as environment variables or read from a centralized repository when the service starts.
2. Abstract: Abstract the access of the configuration data behind a service interface.
3. Centralize: Because a cloud-based application might literally have hundreds of services, it’s critical to minimize the number of different repositories used to hold configuration information.
4. Harden: solution you utilize can be implemented to be highly available and redundant.

# Steps
1. When a microservice instance comes up, it’s going to call a service endpoint to read its configuration information that’s specific to the environment it’s operating in.
2. The actual configuration will reside in a repository. Based on the implementation of your configuration repository, you can choose to use different implementations to hold your configuration data.
3. The actual management of the application configuration data occurs independently of how the application is deployed. Changes to configuration management are typically handled through the build and deployment pipeline where changes of the configuration can be tagged with version information and deployed through the different environments.
4. When a configuration management change is made, the services that use that application configuration data must be notified of the change and refresh their copy of the application data.

## Configuration Management tools
1. Consul
2. Zookeeper
3. Spring cloud **



