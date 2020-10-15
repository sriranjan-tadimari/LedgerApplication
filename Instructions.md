# Curriculum

## Overview

Here we introduce the process of building **Ledger** a Spring Boot microservice application from ground up with MySQL RDBMS and demonstrate how CRUD(Create, Read, Update, Delete) 
operations can be performed using RESTful services.

## Installations

The following tools must be installed locally to demonstrate OR develop the application.

* Java JDK version 8.0.
* MySQL Server and MySQL Command Line tool.
* Spring Tool Suite(3.9.7.RELEASE as of this writing)
* GIT(2.24.1.windows.2 as of this writing)
 

## Objectives

* To develop a simple Spring Boot microservice with Spring Tool Suite and MySQL.
* Constructing RESTful API's.
* Design CRUD operations and implement at the DAO layer.
* Understanding how RESTful API's call Spring JDBCTemplate to perform CRUD operations.

## Instructions

Refer to the [README.MD](https://github.com/sriranjan-tadimari/LedgerApplication/blob/main/README.md) for complete details on installing and 
configuring the application to run locally.

* Launch Spring Tool Suite and create a new Spring Starter Project. 
  * Point out to the students on selections available and they can choose  Web, MySQL and JDBC to create a Spring Boot application with REST and JDBC features.
  * Once the project is generated open [POM.xml](https://github.com/sriranjan-tadimari/LedgerApplication/blob/main/pom.xml) and point out the dependencies for Web, MySQL and JDBC added in the dependencies section.

* Open the file [LedgerTransactionController.java](https://github.com/sriranjan-tadimari/LedgerApplication/blob/main/src/main/java/com/twou/ledgerapp/controller/LedgerTransactionController.java). 
   * Since the Controller class is the component where the REST API's are implemented, ask the students to recognize the various RESTful method implementations. 
   * Explain the concept of Rest URI's and how they map to the methods. 
   * Go through each of the REST API methods and explain how database operations are performed via Data Access Object layer.
   * Explain how the service layer component(DAO) is injected into the Controller via AutoWiring.
   * For a pleasent REST API user experience, point out that the implemented methods handle exceptions gracefully and user making a call to the REST API would not be seeing   any sort of Exceptions.

* Open the [LedgerTransactionDAO.java](https://github.com/sriranjan-tadimari/LedgerApplication/blob/main/src/main/java/com/twou/ledgerapp/dao/LedgerTransactionDAO.java) and [LedgerTransactionDAOImpl.java](https://github.com/sriranjan-tadimari/LedgerApplication/blob/main/src/main/java/com/twou/ledgerapp/dao/LedgerTransactionDAOImpl.java) Data Access Object layer component and explain to the students how JDBCTemplate is used to perform CRUD operations.
