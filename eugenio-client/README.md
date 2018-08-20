# Eugenio Api Client

##About
This is an abstraction of Eugenio Api developed in Java, based on [Eugenio Api documentation](https://portal.stg.eugenio.io/api-docs/#basic-concepts).
In the last version, the followed clients have been implemented:

* Auth Api
* Schema Api
* Ingestion Api
* Query Api
* Thing Api
* Things Invoke Api

##Instructions
###How to run tests
First of all you need fill the pom.xml with valid Eugenio's login information.

```
		<eugenio.username>username_change</eugenio.username>
		<eugenio.password>password_change</eugenio.password>
		<eugenio.tenant>tenant_change</eugenio.tenant>
		<eugenio.uri>https://portal.stg.eugenio.io/api/v1</eugenio.uri>
		<eugenio.apikey>apikey_change</eugenio.apikey>
```

Then build the eugenio-client application using maven by the command below

```
mvn install -DskipTests
```

So you can run the tests using JUnit Test from your IDE or using maven command line

```
mvn test
```

