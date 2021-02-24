# Book-Store

## Import books.sql to create the bookstore database and books table

## To run the project

- Install Maven
  
- Build and test the Spring Boot Project with Maven ```mvn clean install```

- Run Spring Boot app using Maven ```mvn spring-boot:run``` 

## To dockerize the project:

- ```docker build -t book-store/gs-spring-boot-docker .```

- ```docker run -p 3030:3030 book-store/gs-spring-boot-docker```

- The server is running on port 3030
