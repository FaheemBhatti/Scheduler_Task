CREATE DATABASE sensor_data_producersensorsensor;
USE sensor_data_producer;
CREATE TABLE sensor (
  id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  creationTimeStamp timestamp NOT NULL,
  content VARCHAR(5000) NOT NULL
);
