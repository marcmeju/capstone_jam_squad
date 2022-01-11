
-- -----------------------------------------------------
-- Schema not_booking
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `not_booking` ;

-- -----------------------------------------------------
-- Schema not_booking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `not_booking` DEFAULT CHARACTER SET utf8 ;
USE `not_booking` ;

-- -----------------------------------------------------
-- Table `not_booking`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Location` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Location` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `location_city` VARCHAR(45) NOT NULL,
  `location_state` VARCHAR(45) NULL,
  `location_address` VARCHAR(45) NULL,
  `location_zipcode` INT NULL,
  PRIMARY KEY (`location_id`))
;


-- -----------------------------------------------------
-- Table `not_booking`.`Tier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Tier` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Tier` (
  `tier_id` INT NOT NULL AUTO_INCREMENT,
  `tier_name` VARCHAR(45) NULL,
  `tier_desc` VARCHAR(45) NULL,
  PRIMARY KEY (`tier_id`))
;


-- -----------------------------------------------------
-- Table `not_booking`.`Package`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Package` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Package` (
  `package_id` INT NOT NULL AUTO_INCREMENT,
  `package_name` VARCHAR(45) NULL,
  `tier_id` INT NULL,
  `package_price` INT NULL,
  PRIMARY KEY (`package_id`),
  INDEX `tier_id_idx` (`tier_id` ASC) VISIBLE,
  CONSTRAINT `tier_id`
    FOREIGN KEY (`tier_id`)
    REFERENCES `not_booking`.`Tier` (`tier_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking`.`Contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Contact` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Contact` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `contact_type` VARCHAR(45) NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE INDEX `contact_id_UNIQUE` (`contact_id` ASC) VISIBLE)
;


-- -----------------------------------------------------
-- Table `not_booking`.`Event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Event` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `event_type` VARCHAR(45) NOT NULL,
  `event_name` VARCHAR(45) NOT NULL,
  `event_date` DATETIME NOT NULL,
  `event_price` INT NULL,
  `location_id` INT NULL,
  `contact_id` INT NOT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `location_id_idx` (`location_id` ASC) VISIBLE,
  INDEX `fk_Event_Contact1_idx` (`contact_id` ASC) VISIBLE,
  CONSTRAINT `location_id`
    FOREIGN KEY (`location_id`)
    REFERENCES `not_booking`.`Location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Event_Contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `not_booking`.`Contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`user_role` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`user_role_id`))
;


-- -----------------------------------------------------
-- Table `not_booking`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`user` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL,
  `password_hash` VARCHAR(100) NULL,
  `disabled` boolean not null default(0),
  `user_role_id` INT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `user_role_id_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `user_role_id`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `not_booking`.`user_role` (`user_role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Customer` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_first_name` VARCHAR(45) NULL,
  `customer_last_name` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `customer_id_UNIQUE` (`customer_id` ASC) VISIBLE,
  INDEX `fk_Customer_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Customer_Contact1_idx` (`contact_id` ASC) VISIBLE,
  CONSTRAINT `fk_Customer_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `not_booking`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `not_booking`.`Contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking`.`Booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Booking` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Booking` (
  `booking_id` INT NOT NULL AUTO_INCREMENT,
  `package_id` INT NULL,
  `customer_id` INT NULL,
  `num_of_guest` INT NOT NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE INDEX `booking_id_UNIQUE` (`booking_id` ASC) VISIBLE,
  INDEX `package_id_idx` (`package_id` ASC) VISIBLE,
  INDEX `customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `package_id`
    FOREIGN KEY (`package_id`)
    REFERENCES `not_booking`.`Package` (`package_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `not_booking`.`Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking`.`Package_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking`.`Package_event` ;

CREATE TABLE IF NOT EXISTS `not_booking`.`Package_event` (
  `package_event_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NULL,
  `package_id` INT NOT NULL,
  PRIMARY KEY (`package_event_id`),
  UNIQUE INDEX `package_id_UNIQUE` (`package_event_id` ASC) VISIBLE,
  INDEX `fk_Package_event_Package_idx` (`package_id` ASC) VISIBLE,
  INDEX `event_id_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_Package_event_Package`
    FOREIGN KEY (`package_id`)
    REFERENCES `not_booking`.`Package` (`package_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `event_id`
    FOREIGN KEY (`event_id`)
    REFERENCES `not_booking`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

