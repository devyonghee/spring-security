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

create table book
(
    book_id    integer      not null auto_increment,
    title varchar(255) not null,
    body  varchar(255) not null,
    constraint pk_book primary key (book_id)
);