-- MySQL Script generated by MySQL Workbench
-- Wed Oct  6 10:55:24 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users` ;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `account_id` INT NULL,
  `bank_account_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`accounts` ;

CREATE TABLE IF NOT EXISTS `mydb`.`accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` DECIMAL(6,2) NULL,
  `account_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `account_user_id` (`account_user_id` ASC) VISIBLE,
  CONSTRAINT `account_user_id`
    FOREIGN KEY (`account_user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`transactions` ;

CREATE TABLE IF NOT EXISTS `mydb`.`transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `transmitter_id` INT NOT NULL,
  `beneficiary_id` INT NOT NULL,
  `amount` DECIMAL(6,2) NOT NULL,
  `description` VARCHAR(256) NOT NULL,
  `beneficiary_username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `transmitter_id` (`transmitter_id` ASC) VISIBLE,
  CONSTRAINT `transmitter_id`
    FOREIGN KEY (`transmitter_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`connections`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`connections` ;


CREATE TABLE IF NOT EXISTS `mydb`.`connections` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `connection_id` INT NOT NULL,
  `connection_email` VARCHAR(45) NULL,
  `connection_username` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `connection_id` (`connection_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `connection_id`
    FOREIGN KEY (`connection_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `mydb`.`bank_accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`bank_accounts` ;

CREATE TABLE IF NOT EXISTS `mydb`.`bank_accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `iban` VARCHAR(45) NULL,
  `bank_account_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `bank_account_user_id` (`bank_account_user_id` ASC) VISIBLE,
  CONSTRAINT `bank_account_user_id`
    FOREIGN KEY (`bank_account_user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;