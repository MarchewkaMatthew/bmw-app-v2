# bmw-app

## Layers of Microservices

0. Application - Starts our microservice

1. Controller - separates the UI from the front-end,
   describes all endpoints that front-end can call.
   
2. Service - interact with data, contains the business logic

3. Repository - interacts with the DB

### Additional files

1. Entities - represents data (for example: Customer.java)
2. Request - contains the request body

## Remember when adding the new microservice

1. Register the Controller endpoints in the API Gateway (apigw/main/resources/application.yml)
2. add the eureka dependency and add an EurekaClient decorator in the application

### Ports

- 5432 - postgres
- 5050 - pgadmin
- 9411 - zipkin
- 8761 - eureka
- 8083 - API Gateway (main backend port)
- 8080,8081,8082 - microservices

## To run docker

1. Make sure you are in the root directory
2. first run: `docker-compose up -d` (you need docker version > 2.1)
3. next runs: `docker-compose start`

## TODO: In the future

1. For now, we run all the database's in the one Docker container,
we can change that in the `docker-compose.yml`, adding the separate
   service for every microservice database, f.e: postgres-appointment


