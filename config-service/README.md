# Config Service

## Overview
Config Server provides a centralized configuration management system for distributed applications. It fetches configurations from a remote Git repository and serves them to client services.

## Features
- Fetches configurations from a Git repository (public or private)
- Supports environment-specific configuration
- Auto-refresh support for dynamic updates
- Can be secured for private repositories

## Prerequisites
Ensure you have the following installed:
- Java 17 or later
- Maven 3.x
- Git
- Spring Boot 3.x

## Setup and Configuration
### Environment Variables
Set up the following environment variables before running the application:

| Variable Name         | Description                                        | Default Value (if public) | Required for Private Repo |
|----------------------|------------------------------------------------|---------------------------|--------------------------|
| `CONFIG_REPO_URL`   | URL of the Git repository providing configurations | Public repo URL           | ✅ Yes |
| `CONFIG_REPO_USERNAME` | Git username (for private repositories)         | Not required              | ✅ Yes |
| `CONFIG_REPO_TOKEN` | Git token/password (for private repositories)    | Not required              | ✅ Yes |
| `APP_PORT`          | Port on which the Config Server runs             | 7001                      | No |

**Example (.env file for private repo):**
```properties
CONFIG_REPO_URL=https://github.com/example/config-repo
CONFIG_REPO_USERNAME=my-username
CONFIG_REPO_TOKEN=my-access-token
APP_PORT=7021
```

### Running the Application
#### 1. Clone the Repository
```sh
git clone https://github.com/your-repo/config-service.git
cd config-service
```
#### 2. Build the Project
```sh
mvn clean install
```
#### 3. Run the Application
```sh
java -jar target/config-service.jar
```
Alternatively, if using Maven:
```sh
mvn spring-boot:run
```

### Testing the Config Server
Once the server is running, test the configuration retrieval using:
```sh
curl http://localhost:7021/application/default
```
Expected Response (JSON):
```json
{
  "name": "application",
  "profiles": ["default"],
  "label": "master",
  "version": "abc123",
  "propertySources": [
    {
      "name": "https://github.com/example/config-repo/application.properties",
      "source": {
        "app.name": "DemoApp",
        "db.url": "jdbc:mysql://localhost:3306/demo"
      }
    }
  ]
}
```

## Using the Config Server in Client Applications
To use this config server, client applications must set the following in their `bootstrap.yml`:
```yaml
spring:
  application:
    name: your-service-name
  cloud:
    config:
      uri: http://localhost:7021
```

## Securing the Config Server (Optional)
If you want to secure the config server with basic authentication:
1. Add the following to `application.yml`:
   ```yaml
   spring:
     security:
       user:
         name: admin
         password: secret
   ```
2. Access the server with:
   ```sh
   curl -u admin:secret http://localhost:7021/{application}/default
   ```

## Troubleshooting
### 1. Configurations Not Loading
- Ensure the Git repository URL is correct
- Check if the repository is accessible
- For private repositories, ensure correct username/token

### 2. Server Fails to Start
- Ensure Java and Maven are installed
- Check if `APP_PORT` is available or modify it

## License
This project is licensed under the MIT License.

