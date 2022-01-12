
-- -----------------------------------------------------
-- Schema not_booking
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `not_booking_test` ;

-- -----------------------------------------------------
-- Schema not_booking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `not_booking_test` DEFAULT CHARACTER SET utf8 ;
USE `not_booking_test` ;

-- -----------------------------------------------------
-- Table `not_booking_test`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Location` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Location` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `location_city` VARCHAR(45) NOT NULL,
  `location_state` VARCHAR(45) NULL,
  `location_address` VARCHAR(45) NULL,
  `location_zipcode` INT NULL,
  PRIMARY KEY (`location_id`))
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`Tier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Tier` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Tier` (
  `tier_id` INT NOT NULL AUTO_INCREMENT,
  `tier_name` VARCHAR(45) NULL,
  `tier_desc` VARCHAR(45) NULL,
  PRIMARY KEY (`tier_id`))
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`Package`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Package` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Package` (
  `package_id` INT NOT NULL AUTO_INCREMENT,
  `package_name` VARCHAR(45) NULL,
  `tier_id` INT NULL,
  `package_price` INT NULL,
  PRIMARY KEY (`package_id`),
  INDEX `tier_id_idx` (`tier_id` ASC) VISIBLE,
  CONSTRAINT `tier_id`
    FOREIGN KEY (`tier_id`)
    REFERENCES `not_booking_test`.`Tier` (`tier_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`Contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Contact` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Contact` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `contact_type` VARCHAR(45) NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE INDEX `contact_id_UNIQUE` (`contact_id` ASC) VISIBLE)
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`Event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Event` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Event` (
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
    REFERENCES `not_booking_test`.`Location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Event_Contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `not_booking_test`.`Contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`user_role` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NULL,
  PRIMARY KEY (`user_role_id`))
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`User` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL,
  `password_hash` VARCHAR(100) NULL,
  `user_role_id` INT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `user_role_id_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `user_role_id`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `not_booking_test`.`user_role` (`user_role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Customer` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Customer` (
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
    REFERENCES `not_booking_test`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `not_booking_test`.`Contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`Booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Booking` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Booking` (
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
    REFERENCES `not_booking_test`.`Package` (`package_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `not_booking_test`.`Customer` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `not_booking_test`.`Package_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `not_booking_test`.`Package_event` ;

CREATE TABLE IF NOT EXISTS `not_booking_test`.`Package_event` (
  `package_event_id` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NULL,
  `package_id` INT NOT NULL,
  PRIMARY KEY (`package_event_id`),
  UNIQUE INDEX `package_id_UNIQUE` (`package_event_id` ASC) VISIBLE,
  INDEX `fk_Package_event_Package_idx` (`package_id` ASC) VISIBLE,
  INDEX `event_id_idx` (`event_id` ASC) VISIBLE,
  CONSTRAINT `fk_Package_event_Package`
    FOREIGN KEY (`package_id`)
    REFERENCES `not_booking_test`.`Package` (`package_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `event_id`
    FOREIGN KEY (`event_id`)
    REFERENCES `not_booking_test`.`Event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

delimiter //
create procedure set_known_good_state()
begin

	delete from Booking;
    alter table Booking auto_increment = 1;
    delete from Contact;
    alter table Contact auto_increment = 1;
    delete from Customer;
    alter table Customer auto_increment = 1;
    delete from Event;
	alter table Event auto_increment = 1;
    delete from Location;
    alter table Location auto_increment = 1;
    delete from Package;
    alter table Package auto_increment = 1;
    delete from Package_event;
    alter table Package_event auto_increment = 1;
    delete from Tier;
    alter table Tier auto_increment = 1;
    delete from user;
    alter table user auto_increment = 1;
    delete from user_role;
    alter table user_role auto_increment = 1;
    
    
    insert into Contact values
     (1, '213 763 3466', 'lmitchinsonu@de.vu','Event'),
	 (2, '323 857 6000', 'yruttv@state.tx.us','Event'),
     (3, '(464) 7129388', 'craddinl@friendfeed.com', 'Customer');
     
     insert into user_role values
     (1, 'guest'),
     (2,'member'),
     (3,'vip'),
     (4,'admin');
     
     insert into user values
     (1, 'sdesavery0', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
      (2, 'jglasson1', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 2),
       (3, 'ljervis2', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 3),
        (4, 'asarchwell3', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 4);
     
     
     insert into Customer values
     (1, 'Sybilla', 'de Savery', 1, 3 ),
     (14, 'Deerdre', 'Gelsthorpe', 2, 3),
     (20, 'Floria', 'Calverd', 3, 3);
     
	 insert into Location values
      (1, '900 W Exposition Blvd', 'Los Angeles', 'CA', 90007),
      (14, '5700 S Lake Shore Dr','Chicago','IL','60637'),
	  (34, '214 Riverside Dr', 'New York', 'NY', '10025');  
     
	insert into Event values
     (1, 'Museum', 'Natural History Museum of Los Angeles County', '2022-07-16', 35, 1, 1),
     (98, 'Cloud Gate', 'Natural History Museum of Los Angeles County', '2022-07-16', 10, 14, 2),
    (123, ' Restaurant', 'Monteverde Restaurant & Pastificio', '2022-07-15', 125, 34,3),
    (213, 'Aquarium', 'New York Aquarium','2022-07-14', 25, 14, 3),
    (241, 'Ferry', 'East River Ferry', '2022-07-12', 18, 34, 2);
  
    insert into Tier values
    (1, 'Tier 1', 'Express - Two day Package'),
      (2, 'Tier 2', 'Elite -  Three day Package'),
        (3, 'Tier 3', 'VIP -  Customize your Package');
    
   
   insert into Package values
   (1, 'NewYork Express 1 - Museum', 1, 293),
   (27, 'Los Angeles Elite 3', 2, 262 ),
   (14, 'Chicago Express 5 - Attraction', 1, 290),
   (8, 'NewYork Elite 2',2, 246 ),
   (22, 'Los Angeles Express 4 - Aquarium',1, 310),
   (12, 'Chicago Express 3 - Ferry', 1, 317);
   
   insert into Package_event values
   (1,1,1),
   (2,98,27),
   (3,123,14);
   
   
    end //
-- 4. Change the statement terminator back to the original.
delimiter ;
    
    