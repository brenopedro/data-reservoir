create table cube_data (
    id bigint not null auto_increment,
    angular_speedx decimal(19,2),
    angular_speedy decimal(19,2),
    angular_speedz decimal(19,2),
    battery_current decimal(19,2),
    battery_temperature decimal(19,2),
    battery_voltage decimal(19,2),
    euler_anglex decimal(19,2),
    euler_angley decimal(19,2),
    euler_anglez decimal(19,2),
    external_temperature decimal(19,2),
    linear_speedx decimal(19,2),
    linear_speedy decimal(19,2),
    linear_speedz decimal(19,2),
    magnetic_fieldx decimal(19,2),
    magnetic_fieldy decimal(19,2),
    magnetic_fieldz decimal(19,2),
    received_transceiver_power decimal(19,2),
    transmitted_transceiver_power decimal(19,2),
    time_stamp_base datetime(6),
    time_stamp_cube datetime(6),

    primary key (id)
) engine=InnoDB default charset=utf8;

create table drone_data (
    id bigint not null auto_increment,
    altitude decimal(19,2),
    battery_current decimal(19,2),
    battery_voltage decimal(19,2),
    position_gpsx decimal(19,2),
    position_gpsy decimal(19,2),
    position_gpsz decimal(19,2),
    time_stamp_base datetime(6),
    time_stamp_drone datetime(6),

    primary key (id)
) engine=InnoDB default charset=utf8;

create table rocket_data (
    id bigint not null auto_increment,
    acceleration decimal(19,2),
    altitude decimal(19,2),
    angular_speedx decimal(19,2),
    angular_speedy decimal(19,2),
    angular_speedz decimal(19,2),
    euler_anglex decimal(19,2),
    euler_angley decimal(19,2),
    euler_anglez decimal(19,2),
    external_temperature decimal(19,2),
    linear_speedx decimal(19,2),
    linear_speedy decimal(19,2),
    linear_speedz decimal(19,2),
    magnetic_fieldx decimal(19,2),
    magnetic_fieldy decimal(19,2),
    magnetic_fieldz decimal(19,2),
    position_gpsx decimal(19,2),
    position_gpsy decimal(19,2),
    position_gpsz decimal(19,2),
    time_stamp_base datetime(6),
    time_stamp_rocket datetime(6),

    primary key (id)
) engine=InnoDB