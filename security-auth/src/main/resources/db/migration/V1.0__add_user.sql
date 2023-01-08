create table user_account
(
    username varchar(45) null,
    password text null,
    primary key (username)
) engine=InnoDB;

create table otp
(
    username varchar(45) not null,
    code     varchar(45) null,
    primary key (username)
) engine=InnoDB;
