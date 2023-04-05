-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema qagl_ims
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `qagl_ims` ;

-- -----------------------------------------------------
-- Schema qagl_ims
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `qagl_ims` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema qagl_ims
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `qagl_ims` ;

-- -----------------------------------------------------
-- Schema qagl_ims
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `qagl_ims` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `qagl_ims` ;

-- -----------------------------------------------------
-- Table `qagl_ims`.`items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qagl_ims`.`items` ;

CREATE TABLE IF NOT EXISTS `qagl_ims`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `value` DECIMAL NOT NULL,
  `count` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qagl_ims`.`customers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qagl_ims`.`customers` ;

CREATE TABLE IF NOT EXISTS `qagl_ims`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(40) NULL DEFAULT NULL,
  `surname` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `qagl_ims`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qagl_ims`.`orders` ;

CREATE TABLE IF NOT EXISTS `qagl_ims`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATE NOT NULL,
  `customers_id` INT NOT NULL,
  PRIMARY KEY (`id`, `customers_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_orders_customers_idx` (`customers_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_customers`
    FOREIGN KEY (`customers_id`)
    REFERENCES `qagl_ims`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `qagl_ims`.`order_lines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `qagl_ims`.`order_lines` ;

CREATE TABLE IF NOT EXISTS `qagl_ims`.`order_lines` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `orders_id` INT NOT NULL,
  `items_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_lines_orders1_idx` (`orders_id` ASC) VISIBLE,
  INDEX `fk_order_lines_items1_idx` (`items_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_lines_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `qagl_ims`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_lines_items1`
    FOREIGN KEY (`items_id`)
    REFERENCES `qagl_ims`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `qagl_ims` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
