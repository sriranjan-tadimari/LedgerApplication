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
