lock tables cube_data write, drone_data write, rocket_data write;

delete from cube_data;
delete from drone_data;
delete from rocket_data;

alter table cube_data auto_increment = 1;
alter table drone_data auto_increment = 1;
alter table rocket_data auto_increment = 1;

insert into cube_data (id, angular_speedx, angular_speedy, angular_speedz, battery_current, battery_temperature, battery_voltage, euler_anglex, euler_angley, euler_anglez, external_temperature,
                       linear_speedx, linear_speedy, linear_speedz, magnetic_fieldx, magnetic_fieldy, magnetic_fieldz, received_transceiver_power, transmitted_transceiver_power, time_stamp_base, time_stamp_cube, user)
values (1, 1.11, 1.11, 1.11, 111.11, 11.11, 1.11, 11.11, 11.11, 11.11, 111.11, 1111.11, 1111.11, 1111.11, 11.11, 11.11, 11.11, 11111.11, 1111.11, utc_timestamp, utc_timestamp, 'base-station');

insert into cube_data (id, angular_speedx, angular_speedy, angular_speedz, battery_current, battery_temperature, battery_voltage, euler_anglex, euler_angley, euler_anglez, external_temperature,
                       linear_speedx, linear_speedy, linear_speedz, magnetic_fieldx, magnetic_fieldy, magnetic_fieldz, received_transceiver_power, transmitted_transceiver_power, time_stamp_base, time_stamp_cube, user)
values (2, 2.11, 2.11, 2.11, 222.11, 22.11, 2.11, 22.11, 22.11, 22.11, 222.11, 2222.11, 2222.11, 2222.11, 22.11, 22.11, 22.11, 2222.11, 2222.11, utc_timestamp, utc_timestamp, 'admin-client');

insert into drone_data (id, altitude, battery_current, battery_voltage, position_gpsx, position_gpsy, position_gpsz, time_stamp_base, time_stamp_drone, user)
values (1, 1111.11, 111.11, 11.11, 1111.11, 1111.11, 1111.11, utc_timestamp, utc_timestamp, 'base-station');

insert into drone_data (id, altitude, battery_current, battery_voltage, position_gpsx, position_gpsy, position_gpsz, time_stamp_base, time_stamp_drone, user)
values (2, 2222.11, 222.11, 22.11, 2222.11, 2222.11, 2222.11, utc_timestamp, utc_timestamp, 'admin-client');

insert into rocket_data (id, acceleration, altitude, angular_speedx, angular_speedy, angular_speedz, euler_anglex, euler_angley, euler_anglez, external_temperature, linear_speedx, linear_speedy, linear_speedz,
                         magnetic_fieldx, magnetic_fieldy, magnetic_fieldz, position_gpsx, position_gpsy, position_gpsz, time_stamp_base, time_stamp_rocket, user)
values (1, 11.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, 111.11, utc_timestamp, utc_timestamp, 'base-station');

insert into rocket_data (id, acceleration, altitude, angular_speedx, angular_speedy, angular_speedz, euler_anglex, euler_angley, euler_anglez, external_temperature, linear_speedx, linear_speedy, linear_speedz,
                         magnetic_fieldx, magnetic_fieldy, magnetic_fieldz, position_gpsx, position_gpsy, position_gpsz, time_stamp_base, time_stamp_rocket, user)
values (2, 22.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, 222.11, utc_timestamp, utc_timestamp,'admin-client');

unlock tables;