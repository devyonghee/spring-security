create table account
(
    account_id integer                            not null auto_increment,
    username   varchar(255),
    password   varchar(255),
    created_at datetime default CURRENT_TIMESTAMP not null,
    updated_at datetime default CURRENT_TIMESTAMP not null,
    constraint pk_account primary key (account_id),
    constraint uk_account_username unique (username)
);

create table account_user_role
(
    account_user_role_id int auto_increment,
    account_id           int                                not null,
    user_role            varchar(20)                        not null,
    created_at           datetime default CURRENT_TIMESTAMP not null,
    constraint pk_account_user_role primary key (account_user_role_id),
    constraint fk_account_user_role_account_account_id foreign key (account_id) references account (account_id),
    constraint fk_account_user_role_user_role_user_role_role foreign key (user_role) references user_role (role),
    constraint uk_account_user_role_account_id_user_role unique (account_id, user_role)
);

