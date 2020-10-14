DROP TABLE IF EXISTS `ledger`;
CREATE TABLE `ledger` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `sender` VARCHAR(255) NOT NULL,
  `recipient` VARCHAR(255) NOT NULL,
  `soft_delete` TINYINT(1),
  `transaction_value` DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB;