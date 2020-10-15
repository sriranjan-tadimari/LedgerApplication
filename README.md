# Spring Boot Ledger Sample Application 

This project is a simple system that acts as a ledger for financial transactions. This system consists of the following:
* A Spring Boot Java API back-end microservice,
* Standard CRUD service to perform Create, Read, Update and Delete operations using Spring JdbcTemplate,
* Controller endpoint routes specified below in the Restful Controller Endpoints section,
* A MySql Database to store transactions.

##  Restful Controller Endpoints

REST Method | Rest Endpoint | Endpoint Description
------------ | ------------- | -------------------
GET | \<protocol\>:\/\/\<server:port\>/ledger/transactions | Get all transactions that are not soft deleted.
GET | \<protocol\>:\/\/\<server:port\>/ledger/transactions/\<id\> | Get a specific transaction record.
GET | \<protocol\>:\/\/\<server:port\>/ledger/transactions/sum-transacted | Calculate sum of all transacted values that have not been soft deleted.
POST | \<protocol\>:\/\/\<server:port\>/ledger/transactions | Create a new transaction record.
PUT | \<protocol\>:\/\/\<server:port\>/ledger/transactions/\<id\> | Update the transaction value for a record.
DELETE | \<protocol\>:\/\/\<server:port\>/ledger/transactions/\<id\> | Update the soft delete flag for the transaction record without actually deleting it.

## Database configuration

In its default configuration, Ledger uses MySQL database. Please make sure MySQL Server and MySQL Shell is installed.

```
  1. Open MySQL Command Line Client. 
  2. Once you enter the correct password, mysql prompt is displayed.
  4. Execute the following commands in sequence.
      * create database ledger_db;
      * create user ledgeruser@localhost identified by 'LedgerPassword';
      * grant all on ledger_db.* to ledgeruser@localhost;
```
Upon application startup the **ledger** database table is created by the script _/src/main/resources/schema.sql_.
To change the MySQL server configuration including the port, please update the properties file  _/src/main/resources/application.properties_ before application start.

## Running Ledger locally
Ledger is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line.
```
git clone https://github.com/sriranjan-tadimari/LedgerApplication.git
cd LedgerApplication
move mvn .mvn 
mvnw -DskipTests package
java -jar target/LedgerApplication-0.0.1-SNAPSHOT.war
```

If the git clone command throws an error, execute the below statement in git bash and retry cloning the repo.
```
git config --global http.sslBackend schannel
```
These commands were executed on Window 10 OS. Please make appropriate changes when running on Linux OS.

## Usage

Windows and Linux supports using CURL to execute REST calls via Windows Command Prompt or Linux terminal window.

* To get all the transactions that have not been soft deleted:
```
curl -X GET http://localhost:8080/ledger/transactions
```
* To create a new transaction record
```
curl -H "Content-Type: application/json" -X POST  -d "{\"senderName\":\"Peter\",\"recipientName\":\"Paul\"}" http://localhost:8080/ledger/transactions
```
* To get a specific transaction by id with a value of 1
```
curl -X GET http://localhost:8080/ledger/transactions/1
```
* To update the transaction value for a specific transaction by id 
```
curl -X PUT http://localhost:8080/ledger/transactions/1
```
* To delete the transaction by id with a value of 3
```
curl -X DELETE http://localhost:8080/ledger/transactions/3
```
* To get the aggregated transaction value for the records that have not been soft deleted
```
curl -X GET http://localhost:8080/ledger/transactions/sum-transacted
```
