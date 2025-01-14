# currencyexchange-service


## What is this app?
This Spring Boot Micro service  application that integrates with a third-party currency
exchange API to retrieve real-time exchange rates. The application should calculate the total
payable amount for a bill in a specified currency after applying applicable discounts. The
application should expose an API endpoint that allows users to submit a bill in one currency
and get the payable amount in another currency.

## Security
This application end point is secured using jwt role based authentication

## List of softwares/frameworks used
* Java v17
* Spring Boot v3.3.5
* Spring Security v6.x
* Database  h2
* Maven 3.X


## Steps to run the app in workstation

Open CMD and go to the root of the project/Open Terminal and do:

Run below command to download dependencies and generate jar file  
`mvnw.cmd clean install`


Create user request

```bash
$ curl --location 'http://localhost:8080/auth/addNewUser' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "4",
    "name": "ram",
    "email": "xyz@gmail.com",
    "password": "pwd",
    "roles": "ROLE_ADMIN"
}'
```

Response

```bash
 User Added Successfully
```
 
generate Token

```bash
curl --location 'http://localhost:8080/auth/generateToken' \
--header 'Content-Type: application/json' \
--data '{
    "username": "ram",
    "password": "pwd"
}'
```

Response

```bash
eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJyYW0iLCJpYXQiOjE3MzY4MzQzMjIsImV4cCI6MTczNjg0Mjk2Mn0.Rq7pVCyuP3GUhTS8AaXxtSBo7240DU5o6Gt14aSJ0bVAmp16lfqKqoPvPt2t3okp
```
calculate Payable amount

```bash
curl --location 'localhost:8080/api/currency/calculate' \
--header 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJyYW0iLCJpYXQiOjE3MzY4MzQzMjIsImV4cCI6MTczNjg0Mjk2Mn0.Rq7pVCyuP3GUhTS8AaXxtSBo7240DU5o6Gt14aSJ0bVAmp16lfqKqoPvPt2t3okp' \
--header 'Content-Type: application/json' \
--data '{
  "userType": "regular",
  "customerTenure": 3,
  "originalCurrency": "USD",
  "targetCurrency": "EUR",
  "items": [
    {
      "price": "200",
      "category": "electronics"
    },
    {
      "price": "100",
      "category": "mobile"
    }
  ]
}'
```

Response

```bash
263.79
```


### Open swagger
```bash
curl http://localhost:8080/swagger-ui/index.html
```

### Open H2 databse
```bash
curl http://localhost:8080/h2-console/login.jsp
```


### Code Covrage
```bash
{project root directory}/currencyexchange-service/target/site/jacoco/index.html
```
