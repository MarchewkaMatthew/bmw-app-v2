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

## To run docker

1. Make sure you are in the root directory
2. first run: `docker-compose up -d` (you need docker version > 2.1)
3. next runs: `docker-compose start`


