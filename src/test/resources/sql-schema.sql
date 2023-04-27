DROP TABLE IF EXISTS `itemorders`;

DROP TABLE IF EXISTS `orders`;

DROP TABLE IF EXISTS `items`;

DROP TABLE IF EXISTS `customers`;

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

