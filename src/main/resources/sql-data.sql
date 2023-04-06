INSERT INTO `ims`.`warehouses` (`name`, `postcode`) VALUES ("warehouse1", "LA1 3JT");

INSERT INTO `ims`.`drivers` (`first_name`, `surname`, `warehouse_id`) VALUES ("bob", "smith", 1), ("jane", "birch", 1), ("peter", "piper", 1);

INSERT INTO `ims`.`customers` (`first_name`, `surname`, `postcode`) VALUES ("sarah", "brown", "LA1 6YZ");

INSERT INTO `ims`.`orders` (`customer_id`, `driver_id`, `delivered`, `warehouse_id`) VALUES (1, 1, 1, 1), (1, 1, 0, 1);

INSERT INTO `ims`.`items`(`item_name`, `item_value`) VALUES ("mug", 4.99), ("notebook", 6.50), ("chair", 24.99);

