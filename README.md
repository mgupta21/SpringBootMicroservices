## Challenges of traditional
1. Tightly Coupled
2. Leaky : In a traditional model, this data is kept in the same data model and within the same data store. Even though there are obvious boundaries between the data
3. Monolithic : application components for a traditional application reside in a single code base that’s shared across multiple teams, any time a change to the code is made, the entire application has to be recompiled

## Characteristics of microservice based Architecture
1. Constrained : Single set of responsibility and narrow scope
2. Loosely Coupled : collection of small services that only interact with one another through a non–implementation specific interface
3. Abstracted : Microservices completely own their data structures and data sources.
4. Independent : Each microservice in a microservice application can be compiled and deployed independently of the other services used in the application.

## Importance of microservice in cloud
1. A large and diverse user base : Microservices allow features to be delivered quickly (no long release cycles)
2. Extremely high uptime requirements : decentralized nature. isolate faults to specific parts 
3. Uneven volume requirements : easy to scale components horizontally across multiple servers in cloud.

## Microservice Principles
1. A microservice should be self-contained and independently deployable with multiple instances of the service being started up and torn down with a single software artifact.
    a. Service assembly — How do you package and deploy your service to guarantee repeatability and consistency so that the same service code and runtime is deployed exactly the same way?
2. A microservice should be configurable. When a service instance starts up, it should read the data it needs to configure itself from a central location or have its configuration information passed on as environment variables. No human intervention should be required to configure the service.
    a. Service bootstrapping— How do you separate your application and environment-specific configuration code from the runtime code so you can start and deploy a microservice instance quickly in any environment
3. A microservice instance needs to be transparent to the client. The client should never know the exact location of a service. Instead, a microservice client should talk to a service discovery agent that will allow the application to locate an instance of a microservice without having to know its physical location.
    a. Service registration/discovery— When a new microservice instance is deployed, how do you make the new service instance discoverable by other application clients?
4. A microservice should communicate its health. Microservice instances will fail and clients need to route around bad service instances.
    a. Service monitoring— In a microservices environment it’s extremely common for multiple instances of the same service to be running due to high availability needs. Any faults in your microservice should be routed around and ailing service instances taken down.
    
## Twelve-Factor microservice service application
- Codebase — Each microservice should have its own independent code repository within the source control systems.
- Dependencies — Explicitly declare the dependencies your application uses through build tools such as Maven (Java). This allows your microservice to always be built using the same version of libraries.
- Config — Store your application configuration (especially your environment-specific configuration) independently from your code.
- Backing services — You should ensure that at any time, you can swap out your implementation of the database from an in-house managed service to a third-party service.
- Build, release, run — Keep your build, release, and run pieces of deploying your application completely separate. Any changes need to go back to the build process and be redeployed. A built service is immutable and cannot be changed.
- Processes — Your microservices should always be stateless. They can be killed and replaced at any timeout without the fear that a loss-of-a-service instance will result in data loss.
- Port binding — A microservice is completely self-contained with the runtime engine for the service packaged in the service executable. You should run the service without the need for a separated web or application server. The service should start by itself on the command line and be accessed immediately through an exposed HTTP port.
- Concurrency — When you need to scale, don’t rely on a threading model within a single service. Instead, launch more microservice instances and scale out horizontally.
- Disposability — Microservices are disposable and can be started and stopped on demand. Startup time should be minimized and processes should shut down gracefully when they receive a kill signal from the operating system.
- Dev/prod parity — Minimize the gaps that exist between all of the environments in which the service runs (including the developer’s desktop). A developer should use the same infrastructure locally for the service development in which the actual service will run. 
- Logs — Logs are a stream of events. As logs are written out, they should be streamable to tools, such as Splunk (http://splunk.com) or Fluentd (http://fluentd.org)
- Admin processes — Developers will often have to do administrative tasks against their services (data migration or conversion). These tasks should never be ad hoc and instead should be done via scripts that are managed and maintained through the source code repository. These scripts should be repeatable and non-changing (the script code isn’t modified for each environment) across each environment they’re run against.


## Source Code
https://www.safaribooksonline.com/library/view/building-microservices-with/9780134678658/BMSB_07_03.html
https://github.com/livelessons-spring/building-microservices

- There are two maintype of cloud provider. Infrastructure and platform. 
- Infrastructure as a service or IAAS, are things like Amazon EC2or Google Compute Engine. This provides you a virtual machine. 
- Platform as a serviceoperates at a higher level. This is things like Heroku, Cloud Foundry and Google App Engine

