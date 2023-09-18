# Getting Started

### URL Shortner Service

A simple and efficient URL shortening service built with Spring Boot, MongoDB, and Redis.

### Features
* Shorten URLs: Convert long URLs into shortened versions for easy sharing.
* Fast Retrieval: Uses Redis caching for quick retrieval of original URLs.
* Persistent Storage: Stores shortened URLs in MongoDB for persistence.
* TTL Support: Shortened URLs have a Time-To-Live (TTL) of 30 days in Redis.
* Validation: Ensures that only valid URLs are shortened.
* Error Handling: Gracefully handles errors and provides meaningful error messages.


### Prerequisites

* Java 17
* Maven
* Mongo DB
* Redis


### Getting Started

1. Clone the Repository
*  git clone https://github.com/charlig/shortened-url-service.git


2. Configure MongoDB and Redis
* Ensure that MongoDB and Redis are running. (You can use "docker compose up" command to run using the composer)
* Update the application.properties or application.yml file with the appropriate connection details if they differ from the default.

3. Running the code
* mvn spring-boot:run
