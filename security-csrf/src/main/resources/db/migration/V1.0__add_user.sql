create table token
(
    id         integer not null auto_increment,
    identifier varchar(45) null,
    token      text null,
    primary key (id)
);
