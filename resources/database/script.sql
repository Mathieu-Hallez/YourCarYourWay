DROP TABLE `Translation`;
DROP TABLE `Picture`;
DROP TABLE `TranslationDictionnary`;
DROP TABLE `Message`;
DROP TABLE `Offer`;
DROP TABLE `Agency`;
DROP TABLE `Vehicle`;
DROP TABLE `PictureType`;
DROP TABLE `Conversation`;
DROP TABLE `User`;
DROP TABLE `UserRole`;
DROP TABLE `CreditCard`;
DROP TABLE `Language`;
DROP TABLE `Address`;

CREATE TABLE IF NOT EXISTS `Address` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `street_number` INTEGER NOT NULL,
  `street_name` VARCHAR(500) NOT NULL,
  `postcode` INTEGER NOT NULL,
  `locality_name` VARCHAR(255) NOT NULL,
  `country` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Language` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `language_code` VARCHAR(5) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `culture_code` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `CreditCard` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `cb_number` VARCHAR(16) NOT NULL,
  `expiration_date` DATE NOT NULL,
  `crypto_code` VARCHAR(3) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `UserRole` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `User` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `firstname` VARCHAR(255) NOT NULL,
  `birth_date` DATE NOT NULL,
  `phone_number` VARCHAR(10),
  `role` INTEGER NOT NULL,
  `credit_card_id` INTEGER,
  `address_id` INTEGER NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`address_id`) REFERENCES `Address`(`id`),
  FOREIGN KEY (`role`) REFERENCES `UserRole`(`id`)
);

CREATE TABLE IF NOT EXISTS `Conversation` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(255) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `user1_id` INTEGER NOT NULL,
  `user2_id` INTEGER,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user2_id`) REFERENCES `User`(`id`),
  FOREIGN KEY (`user1_id`) REFERENCES `User`(`id`),
  CONSTRAINT `CHK_users` CHECK (`user1_id` <> `user2_id`)
);

CREATE TABLE IF NOT EXISTS `PictureType` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Vehicle` (
  `number_plate` VARCHAR(15) NOT NULL,
  `category` VARCHAR(255) NOT NULL,
  `type` VARCHAR(255) NOT NULL,
  `mileage` FLOAT NOT NULL,
  `condition` VARCHAR(255) NOT NULL,
  `number_of_door` INTEGER NOT NULL,
  `transmission` VARCHAR(255) NOT NULL,
  `fuel` VARCHAR(255) NOT NULL,
  `transport_capacity` FLOAT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`number_plate`)
);

CREATE TABLE IF NOT EXISTS `Agency` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(10) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `address_id` INTEGER NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Offer` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `start_date` DATETIME NOT NULL,
  `finish_date` DATETIME NOT NULL,
  `tarif` FLOAT NOT NULL,
  `start_agency_id` INTEGER NOT NULL,
  `finish_agency_id` INTEGER NOT NULL,
  `vehicle_id` VARCHAR(15) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`vehicle_id`) REFERENCES `Vehicle`(`number_plate`),
  FOREIGN KEY (`start_agency_id`) REFERENCES `Agency`(`id`),
  FOREIGN KEY (`finish_agency_id`) REFERENCES `Agency`(`id`)
);

CREATE TABLE IF NOT EXISTS `Message` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `text` TEXT NOT NULL,
  `sender_id` INTEGER NOT NULL,
  `conversation_id` INTEGER NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`sender_id`) REFERENCES `User`(`id`),
  FOREIGN KEY (`conversation_id`) REFERENCES `Conversation`(`id`)
);

CREATE TABLE IF NOT EXISTS `TranslationDictionnary` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `key` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Picture` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `blob` LONGBLOB NOT NULL,
  `size` INTEGER NOT NULL,
  `type` INTEGER NOT NULL,
  `vehicle_id` VARCHAR(15) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`type`) REFERENCES `PictureType`(`id`),
  FOREIGN KEY (`vehicle_id`) REFERENCES `Vehicle`(`number_plate`)
);

CREATE TABLE IF NOT EXISTS `Translation` (
  `language_id` INTEGER NOT NULL,
  `translation_dictionnary_id` INTEGER NOT NULL,
  `text` TEXT NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`language_id`) REFERENCES `Language`(`id`),
  FOREIGN KEY (`translation_dictionnary_id`) REFERENCES `TranslationDictionnary`(`id`),
  PRIMARY KEY (`language_id`, `translation_dictionnary_id`)
);

INSERT INTO Address (street_number, street_name, locality_name, postcode, country) VALUES ('5', 'Rue des fougère', 'Bourges', 18033, 'France');
INSERT INTO Address (street_number, street_name, locality_name, postcode, country) VALUES ('104', 'Rue des thons', 'Nantes', 44000, 'France');
INSERT INTO UserRole (name) VALUES ('CLIENT');
INSERT INTO UserRole (name) VALUES ('SUPPORT');
INSERT INTO User (email, password, lastname, firstname, birth_date, address_id, role) VALUES ('client1@test.com', 'client1!', '1', 'client', '1995-05-25', '1', '1');
INSERT INTO User (email, password, lastname, firstname, birth_date, address_id, role) VALUES ('client2@test.com', 'client2!', '2', 'client', '1995-05-25', '2', '1');
INSERT INTO User (email, password, lastname, firstname, birth_date, address_id, role) VALUES ('support1@test.com', 'support1', '1', 'support', '2000-10-07', '1', '2');

