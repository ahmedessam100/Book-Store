CREATE DATABASE bookstore;

CREATE TABLE `books` ( `id` int(11) NOT NULL AUTO_INCREMENT, `title` varchar(50) NOT NULL, `author_name` varchar(70), `release_date` DATE, `description`
varchar(150), PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=6;
