USE is_people;

CREATE TABLE `t_people` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(90) NOT NULL,
  `pin` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);

INSERT INTO `t_people` (`full_name`, `pin`) VALUES ('Ivan Dimitrov', '6130000389');
INSERT INTO `t_people` (`full_name`, `pin`) VALUES ('Ivan Asenov', '1000215035');
INSERT INTO `t_people` (`full_name`, `pin`) VALUES ('Angel Yordanov', '0316848430');
INSERT INTO `t_people` (`full_name`, `pin`) VALUES ('Milen Atanasov', '8415313138');
INSERT INTO `t_people` (`full_name`, `pin`) VALUES ('Milena Raykova', '0131351515');
INSERT INTO `t_people` (`full_name`, `pin`) VALUES ('Stanislava Yordanova', '1645486021');

CREATE TABLE `t_mails` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `t_people_id` INT NOT NULL,
  `email_type` VARCHAR(5) NOT NULL,
  `email` VARCHAR(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `a_idx` (`t_people_id` ASC) VISIBLE,
  CONSTRAINT `t_people_id_m`
    FOREIGN KEY (`t_people_id`)
    REFERENCES `is_people`.`t_people` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO `t_mails` (`t_people_id`, `email_type`, `email`) VALUES ('1', 'type1', 'ivan1@abv.bg');
INSERT INTO `t_mails` (`t_people_id`, `email_type`, `email`) VALUES ('2', 'type2', 'ivan222@mail.bg');
INSERT INTO `t_mails` (`t_people_id`, `email_type`, `email`) VALUES ('3', 'type2', 'angel1@abv.bg');
INSERT INTO `t_mails` (`t_people_id`, `email_type`, `email`) VALUES ('4', 'type1', 'milen1@gmail.com');
INSERT INTO `t_mails` (`t_people_id`, `email_type`, `email`) VALUES ('5', 'type1', 'milena1@mail.bg');
INSERT INTO `t_mails` (`t_people_id`, `email_type`, `email`) VALUES ('6', 'type1', 'stasi1@abv.bg');

CREATE TABLE `t_addresses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `t_people_id` INT NOT NULL,
  `addr_type` VARCHAR(5) NOT NULL,
  `addr_info` VARCHAR(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `t_people_id_idx` (`t_people_id` ASC) VISIBLE,
  CONSTRAINT `t_people_id_a`
    FOREIGN KEY (`t_people_id`)
    REFERENCES `is_people`.`t_people` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

INSERT INTO `t_addresses` (`t_people_id`, `addr_type`, `addr_info`) VALUES ('1', 'perm', 'Sofia, Druzhba 108');
INSERT INTO `t_addresses` (`t_people_id`, `addr_type`, `addr_info`) VALUES ('2', 'cur', 'Sofia, Mladost 333');
INSERT INTO `t_addresses` (`t_people_id`, `addr_type`, `addr_info`) VALUES ('3', 'perm', 'Sofia, Lozenec 50');
INSERT INTO `t_addresses` (`t_people_id`, `addr_type`, `addr_info`) VALUES ('4', 'perm', 'Burgas, Izgrev 10');
INSERT INTO `t_addresses` (`t_people_id`, `addr_type`, `addr_info`) VALUES ('5', 'cur', 'Burgas, Lazur 1');
INSERT INTO `t_addresses` (`t_people_id`, `addr_type`, `addr_info`) VALUES ('6', 'perm', 'Sofia, Druzhba 214');
