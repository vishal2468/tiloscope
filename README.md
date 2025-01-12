# Tiloscope

Tiloscope is a web application for creating, managing, and interacting with boards in a community-driven platform. It allows users to register, create boards, share them, upvote, and view user profiles. The application includes features like responsive design and quick loading, ensuring an optimal user experience.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.4.1
- **Build Tool**: Gradle 7.4+
- **Database**: PostgreSQL RDB
- **Frontend**: ([Frontend](https://github.com/Rohan045/tiloscope.git))

## Features
- User registration and login
- Create, manage, and share boards
- Upvote boards and posts
- User profile management
- Responsive and fast UI

## Installation

### Prerequisites
- Java 17 or higher
- Gradle 7.4+ installed
- PostgreSQL RDB database

### Clone the Repository
git clone https://github.com/vishal2468/tiloscope.git


### Setup PostgreSQL
psql -U postgres -c "CREATE DATABASE tiloscope;" 
psql -U postgres -c "CREATE USER tiloscope_user WITH ENCRYPTED PASSWORD 'password';" 
psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE tiloscope TO tiloscope_user;"


### Configure Database Connection in `application.properties`
spring.datasource.url=jdbc:postgresql://localhost:5432/tiloscope 
spring.datasource.username=tiloscope_user 
spring.datasource.password=password 
spring.datasource.driver-class-name=org.postgresql.Driver 
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect



### Build and Run the Application
cd tiloscope ./gradlew build ./gradlew bootRun


## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
