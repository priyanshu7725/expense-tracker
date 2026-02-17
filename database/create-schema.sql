CREATE DATABASE IF NOT EXISTS expense_tracker;
USE expense_tracker;

DROP TABLE IF EXISTS expense;

CREATE TABLE expense (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    amount DOUBLE NOT NULL,
    category VARCHAR(255) NOT NULL);
