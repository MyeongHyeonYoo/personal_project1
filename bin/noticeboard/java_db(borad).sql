CREATE TABLE `boards` (
  `bno` int NOT NULL AUTO_INCREMENT,
  `btitle` varchar(100) NOT NULL,
  `bcontent` longtext NOT NULL,
  `bwriter` varchar(50) NOT NULL,
  `bdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3