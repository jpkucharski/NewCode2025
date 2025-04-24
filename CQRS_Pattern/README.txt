CQRS pattern example: MySql databases replication.
App connected to two MySql databases with internally implemented replication.
Write operations are preformed on master, reads on slave.

To run application:
1.  Use docker command to create MySQL servers and initialize data:
    docker-compose up -d

2.  Create jar:
    mvn clean install

3.  To build image:
    docker build -t cqrs_app .

4.  To run app in separate container in docker:
    docker run -d --network cqrs_pattern_mysql_network --name cqrs_app_container -p 8080:8080 cqrs_app --spring.profiles.active=docker

Endpoints:
localhost:8080/status <- to GET status of server
localhost:8080/insert <- to insert member using JSON format in body of POST method
{
        "firstname": "firstname4",
        "lastname" : "lastname4",
        "email" : "email4@email.com"
}

localhost:8080/members <- GET all members from slave MySql server