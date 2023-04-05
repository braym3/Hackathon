DROP TABLE IF EXISTS `ims`.`itemorders`;

DROP TABLE IF EXISTS `ims`.`orders`;

DROP TABLE IF EXISTS `ims`.`items`;

DROP TABLE IF EXISTS `ims`.`customers`;

drop schema if exists ims;

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

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `item_value` INT(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `order_name` VARCHAR(40) DEFAULT NULL,
    `customer_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `itemorders`
(
	`item_id` int(11) NOT NULL,
    `order_id` int(11) NOT NULL,
    `orderquant` int(11) NOT NULL,
    primary key (`item_id`, `order_id`),
    foreign key(`item_id`) references `items`(`id`) ON DELETE CASCADE,
	foreign key(`order_id`) references `orders`(`id`) ON DELETE CASCADE
    );