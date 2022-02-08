



<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#running-application">Running Application</a>
    </li>
    <li>
      <a href="#usage">Usage</a>
    </li>
    <li>
        <a href="#development">Development</a>
        <ul>
            <li><a href="#build-docker-image">Built Docker Image</a></li>
        </ul>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
The project provides the REST API of a simple book ordering application

## Running Application

1. Clone the repo
2. How to run the application
   ```sh
   cd Docker
   docker-compose up -d
   ```


## Usage

- How to access OpenAPI definition and Swagger UI
  
    http://localhost:8080/swagger-ui.html

- In the project, h2 database is used.
- H2 Console can be accessed with the following URL http://localhost:8080/h2-console
  * JDBC URL: jdbc:h2:mem:testdb 
  * username: sa
  * password: password
           

## Development

1. Clone the repo
2. How to run this application using maven wrapper
   ```sh
   mvnw spring-boot:run
   ```
3. How to run tests from terminal
   ```sh
   mvnw test
   ```
### Build Docker Image
1. How to build project
    ```sh
   mvn clean package
   ```
2. How to create docker image
   ```sh
   docker image build -t <name-of-the-image> .
   ```
   

