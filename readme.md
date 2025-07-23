** Application has: **

1) Service discovery
2) Centralized configuration
3) Distributed tracing 
4) Event driven architecture 
5) Centralized loggin
6) Circuit breaker 
7) Secure microservices using keyloak


** SERVICES **
Spring cloud, eureka, config server in spring cloud 
1) Product service: create and view products acts as product catalog
2) order service: Can order products 
3) notification service: Can check if the product is in stock
4) Inventory service : Can send notifications, after the order is placed

   Order, Inventory Notifications service are gng to interact

Synchronous and async communications are available 

Client talks to the API gateway in the spring cloud
API gateway diverts the request to the required service
Keyload for the authentication 

-> Product service : MongoDB
-> Order service: Postgresql
-> Invemntory Service:: Postgresql
-> Notification service: 

Resilience4J: is the async communication, Kafka for order and notification service


HTTP ----> Controller --> service ---> Repository 
