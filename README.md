# CapstoneProject

Simple Spring boot app for managing, assigning parcels, drivers to buses.
Project as a part the Java course.

How to run an application: <br />
-start docker container with postgres in it <br />
$ docker start <ID CONTAINER>
-when application is run for the first time, uncomment this line <br />
#spring.jpa.hibernate.ddl-auto=create <br />
In file application.properties <br />
-next step is start the application in  intelij or .jar file in terminal <br />
URL address to application: http://localhost:8080/ <br />


List of endpoints: <br />

- /parcels POST GET DELETE <br />
- /drivers POST GET DELETE <br />
- /deliveryBuses POST GET DELETE <br />
