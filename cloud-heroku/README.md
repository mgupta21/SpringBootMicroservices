# Intro
- You can do two things with Heroku. You can either push binaries directly to the service, or you can link a git repository and push source code, and Heroku will build your files
- Either way, Heroku uses a Procfile that defines how your application is started. (defined in pom)
java -Dserver.port=$PORT - jar target/yourapp-0.0.1.jar

# Prerequisites
heroku login
heroku create -n
- Created application name is used in next step

# Pushing to Heroku
mvn clean package heroku:deploy -Dheroku.appName=<appname>
- eg: mvn clean package heroku:deploy -Dheroku.appName=shrouded-mountain-74304

# Running the app
http://shrouded-mountain-74304.herokuapp.com
 