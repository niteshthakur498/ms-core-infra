# Spring Cloud Discovery Service (Netflix Eureka)

## Overview
The **Discovery Service** is a **Spring Boot application** that acts as a **Netflix Eureka Server**, allowing microservices to register themselves and discover other services dynamically. This enables a fully scalable and distributed system.

## Features
- Centralized service registry using **Netflix Eureka**
- Supports dynamic service discovery
- Health checks for registered services
- Configurable using **Spring Cloud Config**
- Can be deployed as a **standalone server** or in **high availability (HA) mode**

## Prerequisites
Ensure you have the following installed:
- **Java 17** or later
- **Maven 3.x**
- **Spring Boot 3.x**

## Setup and Configuration
### **Environment Variables**
Set up the following environment variables before running the application:

| Variable Name        | Description                                    | Default Value            | Required |
|----------------------|----------------------------------------------|--------------------------|----------|
| `CONFIG_SERVER_URL` | URL of the Spring Cloud Config Server       | `http://localhost:7001` | No       |
| `APP_PORT`         | Port on which the Discovery Service runs     | `8761`                   | No       |
| `DISCOVERY_SERVER_URL` | Eureka Server URL (if clustered)       | `http://localhost:8761/eureka` | No |

**Example (.env file for customization):**
```properties
CONFIG_SERVER_URL=http://config-server:7001
APP_PORT=8761
DISCOVERY_SERVER_URL=http://eureka-server:8761/eureka
```

### **Running the Application**
#### **1. Clone the Repository**
```sh
git clone https://github.com/your-repo/discovery-service.git
cd discovery-service
```

#### **2. Build the Project**
```sh
mvn clean install
```

#### **3. Run the Application**
```sh
java -jar target/discovery-service.jar
```
Alternatively, if using Maven:
```sh
mvn spring-boot:run
```

### **Access Eureka Dashboard**
Once the server is running, open your browser and navigate to:
```
http://localhost:8761
```
You should see the **Eureka dashboard**, where registered services appear.

## **Registering Client Services**
To allow other microservices to register with Eureka, they need to add the following to their `application.yml`:
```yaml
spring:
  application:
    name: my-microservice

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
```

## **High Availability (HA) Setup**
For **HA mode**, run multiple Eureka instances and configure them to talk to each other:
```yaml
eureka:
  client:
    serviceUrl:
      defaultZone: http://eureka-peer1:8761/eureka/,http://eureka-peer2:8762/eureka/
  server:
    enableSelfPreservation: true
```

## **Securing Eureka (Optional)**
To protect the Eureka dashboard, enable basic authentication:
```yaml
spring:
  security:
    user:
      name: admin
      password: secret
```
Access with:
```sh
curl -u admin:secret http://localhost:8761/eureka
```

## **Troubleshooting**
### **1. No Services Appear in the Dashboard**
- Ensure client applications have `eureka.client.serviceUrl.defaultZone` configured correctly.
- Check the Eureka logs for errors.

### **2. Configurations Not Loading**
- Verify the `CONFIG_SERVER_URL` is reachable.
- Ensure the Config Server is running before starting Eureka.


