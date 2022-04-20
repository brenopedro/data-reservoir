ALTER TABLE `data_reservoir`.`drone_data`
    ADD COLUMN `user` VARCHAR(45) NOT NULL AFTER `time_stamp_drone`;

ALTER TABLE `data_reservoir`.`rocket_data`
    ADD COLUMN `user` VARCHAR(45) NOT NULL AFTER `time_stamp_rocket`;