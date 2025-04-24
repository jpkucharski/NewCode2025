CREATE TABLE `members` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO members (id, firstname, lastname, email) VALUES
(1, "firstname1", "lastname1", "email1@email.com"),
(2, "firstname2", "lastname2", "email2@email.com"),
(3, "firstname3", "lastname3", "email3@email.com");