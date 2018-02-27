# pushing to cloud foundry
mvn clean package
cf push <appname> -p target/livelessons-cloud-cloudfoundry-1.0.0-SNAPSHOT.jar