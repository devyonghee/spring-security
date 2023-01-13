create table product
(
    id    integer not null auto_increment,
    name  varchar(45) null,
    owner varchar(45) null,
    primary key (id)
) engine=InnoDB;

insert into product (id, name, owner)
values (1, 'beer', 'readUser'),
       (2, 'candy', 'readUser'),
       (3, 'chocolate', 'writeUser');
