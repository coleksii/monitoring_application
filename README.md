# Monitoring Usage API.

## Running application locally

to run test
```bash
mvn clean test
```

to run application you need to run command:
```bash
mvn clean spring-boot:run
```

Local path will be accessible at
http://localhost:8080/monitoring-api/

There are 3 endpoints:

1. GET [/monitoring-api/users](http://localhost:8080/monitoring-api/users) to found all users
2. GET [/monitoring-api/measurement](http://localhost:8080/api/monitoring/measurement?phoneNumber=312) for getting measurement usage by user <br>
phoneNumber is required parameter, you can get it from previous endpoint
3. POST [/monitoring-api/measurement](http://localhost:8080/monitoring-api/measurement) for saving measurement<br>
phoneNumber you can get from first endpoint<br>
You can check saved measurement with previous controller<br>
Example body:
```json
{
   "gas": 123,
   "hotWater": 444,
   "coldWater": 4124,
   "phoneNumber": "312",
   "comment": "Finnaly, I got new measurement!"
}
   ```

You can find swagger documentation on [swagger](http://localhost:8080/monitoring-api/swagger-ui/#/monitoring-controller)

The database will contain some init data with some users.

## Database
All data will be saved to HSQLDB
HSQLDB server run with launch application, so when you shut down application data will be erased 

To connect to database after launching application use thees default for HSQLD credentials:<br>
url: jdbc:hsqldb:hsql://localhost:9001/monitoringdb<br>
user: SA<br>
password: (EMPTY)<br>

Init database structure table make Hibernate with DATA JPA enteties.
Init data script you can find at resources/data.sql, that init some users and measurement.



I defined tables through data jpa entities, and it generates them each launch.
It is not the best way to create database, but for case in memory db - it is good idea, especially if you want to show knowledge about jpa annotations.
For real database you should go with normal scripts, moreover if you have complicated db - some frameworks for migrations are required like flyway.

## Profiles
Now it is only for locally runing. So now active profile - local

For usage on server you need change profile and add new config for connecting to database (in application.properties/Config.java, or with env variables)

## To Do in future releases
- make support save user<br>
- make penalty db support<br>
- move location to separate table<br>
- add codestyle support<br>