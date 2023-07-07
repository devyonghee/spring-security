create table url_endpoint
(
    url_endpoint_id int                                not null auto_increment,
    url_pattern     varchar(255),
    http_method     varchar(10),
    created_at      datetime default CURRENT_TIMESTAMP not null,
    updated_at      datetime default CURRENT_TIMESTAMP not null,
    constraint pk_url_endpoint primary key (url_endpoint_id),
    constraint uk_url_endpoint_url_pattern_http_method unique (url_pattern, http_method)
);

create table user_role
(
    role varchar(20) not null,
    constraint pk_user_role primary key (role)
);

create table user_role_url_endpoint
(
    user_role_url_endpoint_id int auto_increment,
    user_role                 varchar(20)                        not null,
    url_endpoint_id           int                                not null,
    created_at                datetime default CURRENT_TIMESTAMP not null,
    updated_at                datetime default CURRENT_TIMESTAMP not null,
    constraint pk_user_role_url_endpoint primary key (user_role_url_endpoint_id),
    constraint fk_user_role_url_endpoint_user_role_role foreign key (user_role) references user_role (role),
    constraint fk_user_role_url_endpoint_url_endpoint_url_endpoint_id foreign key (url_endpoint_id) references url_endpoint (url_endpoint_id),
    constraint uk_user_role_url_endpoint_user_role_url_endpoint unique (user_role, url_endpoint_id)
);

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


create table book
(
    id    integer      not null auto_increment,
    body  varchar(255) not null,
    title varchar(255) not null,
    constraint pk_book primary key (id)
);