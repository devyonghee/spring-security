insert into user_role (role)
values ('ADMIN'),
       ('CLIENT'),
       ('ANONYMOUS');


insert url_endpoint (url_endpoint_id, url_pattern, http_method)
values (1, '/books', 'GET'),
       (2, '/books/*', 'GET'),
       (3, '/books', 'POST');

insert user_role_url_endpoint(user_role, url_endpoint_id)
values ('ADMIN', 2),
       ('ADMIN', 3),
       ('CLIENT', 2),
       ('ANONYMOUS', 1);

insert book(title, body)
values ('title', 'body');