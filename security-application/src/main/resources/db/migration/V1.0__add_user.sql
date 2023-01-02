create table product
(
    id       integer not null auto_increment,
    currency varchar(255),
    name     varchar(255),
    price    decimal(38, 2),
    primary key (id)
) engine=InnoDB;

create table user_account
(
    id                   integer not null auto_increment,
    encryption_algorithm varchar(255),
    password             varchar(255),
    username             varchar(255),
    primary key (id)
) engine=InnoDB;

create table authority
(
    id      integer not null auto_increment,
    name    varchar(255),
    user_id integer,
    primary key (id)
) engine=InnoDB;

alter table authority
    add constraint authority_user_id foreign key (user_id) references user_account (id);

INSERT INTO security.user_account (id, encryption_algorithm, password, username)
VALUES (1, 'BCRYPT', '$2a$10$r/TnndPNEpW6qhQKzxnohue.Y2UYjFpBmVR0MXbmGvjxWsybdUm/i', 'yong'),
       (2, 'BCRYPT', '$2a$10$r/TnndPNEpW6qhQKzxnohue.Y2UYjFpBmVR0MXbmGvjxWsybdUm/i', 'yo');

INSERT INTO security.authority (id, name, user_id)
VALUES (1, 'read', 1),
       (2, 'write', 1),
       (3, 'write', 2);

INSERT INTO security.product (id, currency, name, price)
VALUES (1, 'USD', 'product1', 10.00),
       (2, 'USD', 'product2', 10.00);
