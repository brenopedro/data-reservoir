ALTER TABLE `data_reservoir`.`cube_data`
    ADD COLUMN `user` VARCHAR(45) NOT NULL AFTER `time_stamp_cube`,
CHANGE COLUMN `time_stamp_base` `time_stamp_base` DATETIME(6) NOT NULL ;