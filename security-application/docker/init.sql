-- # create databases
CREATE DATABASE IF NOT EXISTS `gift3` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE IF NOT EXISTS `gift_auth` DEFAULT CHARACTER SET utf8mb4;
CREATE DATABASE IF NOT EXISTS `exchange_rate` DEFAULT CHARACTER SET utf8mb4;

create table exchange_rate.exchange_rate
(
    id           varchar(36)    not null primary key,
    rate         decimal(10, 6) not null,
    pair         varchar(7)     not null,
    base_time    datetime       null,
    provider     varchar(30)    null,
    retrieved_at datetime       null,
    index exchange_rate_base_time_index (base_time)
);

create table exchange_rate.shedlock
(
    name       VARCHAR(64) PRIMARY KEY,
    lock_until TIMESTAMP NULL,
    locked_at  TIMESTAMP NULL,
    locked_by  VARCHAR(255)
) charset = utf8mb4;
