# Spring Boot Ledger Sample Application 

This project is a simple system that acts as a ledger for financial transactions. This system consists of the following:
* A Spring Boot Java API back-end microservice,
* Standard CRUD service to perform create, read, update, and delete operations using Spring JdbcTemplate,
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

## Running Ledger locally
Ledger is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:
```
git clone https://github.com/sriranjan-tadimari/LedgerApplication.git
cd LedgerApplication
./mvnw package
java -jar target/*.jar

P.S. If you face issue when cloning the repo, execute the below statement in git bash and retry cloning the repo.
git config --global http.sslBackend schannel
```


