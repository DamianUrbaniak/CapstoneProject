# CapstoneProject

Simple Spring boot app for managing, assigning parcels, drivers to buses.
Project as a part the Java course.

How to run an application:
-start docker container with postgres in it
$ docker start <ID CONTAINER>
-when application is run for the first time, uncomment this line
#spring.jpa.hibernate.ddl-auto=create
In file application.properties
-next step is start the application in  intelij or .jar file in terminal
URL address to application: http://localhost:8080/


List of endpoints:

- /parcels POST GET DELETE
- /drivers POST GET DELETE
- /deliveryBuses POST GET DELETE
