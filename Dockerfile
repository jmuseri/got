# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="jmuseri@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8180 available to the world outside this container
EXPOSE 8180

# The application's jar file
ARG JAR_FILE=target/gotApi-0.2.4.war


# Add the application's jar to the container
ADD ${JAR_FILE} gotApi.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gotApi.jar"]