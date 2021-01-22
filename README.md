# northern-countries

[![Build Status](https://travis-ci.org/Akash-Mittal/northern-countries.svg?branch=master)](https://travis-ci.org/Akash-Mittal/northern-countries)
[![GitHub issues](https://img.shields.io/github/issues/Akash-Mittal/northern-countries.svg)](https://github.com/Akash-Mittal/northern-countries/issues)
[![GitHub forks](https://img.shields.io/github/forks/Akash-Mittal/northern-countries.svg)](https://github.com/Akash-Mittal/northern-countries/network)
[![GitHub stars](https://img.shields.io/github/stars/Akash-Mittal/northern-countries.svg)](https://github.com/Akash-Mittal/northern-countries/stargazers)
[![GitHub license](https://img.shields.io/github/license/Akash-Mittal/northern-countries.svg)](https://github.com/Akash-Mittal/northern-countries/blob/master/LICENSE)


## Overview

	An Async REST service that takes IP addresses, checks which countries they are from and if there are any returns list
	of countries from the northern hemisphere. 

	* In order to get information about IP addresses API uses IP Vigilante API: https://www.ipvigilante.com/api-developer-docs/.
		
	* Uses latitude value to decide if IP address comes from a country from the northern hemisphere.

## Requirements for the service API:

	* Service exposes one endpoint for a GET request
	* The endpoint should accept list of IP addresses passed as request parameters
	* The endpoint should accept at least 1 and maximum 5 ip addresses
	* If among the passed IP addresses there are addresses from countries from the northern hemisphere, service should return these country names.
	* Response contains list of unique names (no repetitions of names) sorted alphabetically

### Request:
	
	curl "http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0"

### Response: 

	{  
	   "northcountries":[  
	      "Colombia",
	      "Japan",
	      "United States"
	   ]
	}

## Accessing API from Cloud.

Swagger(Heroku):

https://northern-countries.herokuapp.com/swagger-ui.html#/

Without Swagger:

https://northern-countries.herokuapp.com/api/v1/northcountries?ip=8.8.8.8

## Bench Marks.

	* Internally uses Spring's `@Async` and Java's `CompletableFuture<?>`
	* For N requests time taken is Max(N) Spindle time.
	* For example for below scenario the Approximate response time 5 Seconds.
		* IP Call 1 Takes 2 Seconds 
		* IP Call 2 Takes 5 Seconds 
		* IP Call 3 Takes 1 Seconds 
		* IP Call 4 Takes 1 Seconds 
	* This is because all for requests are made in parallel.

## Some Points

	* The API can be tested via Swagger.
	* The Test Case Coverage is About 60% With Integration Tests.
	* There is more scope of Improvement/Re Factoring needed in terms of design(Also some properties should be externalised).
	* Service can be started using ` java -jar target\finder-0.0.1-SNAPSHOT.jar from project root `.
	* Please make sure you run ` maven clean install -DskipTests`.
