set foreign_key_checks = 0;

delete from user;

set foreign_key_checks = 1;

alter table user auto_increment = 1;

insert into user (id, createdAt, name, email, password, role) values (1, utc_timestamp, 'Maria', 'maria@alura.com.br', '12345', 'INSTRUCTOR');
insert into user (id, createdAt, name, email, password, role) values (2, utc_timestamp, 'Milene', 'milene@gmail.com', '12345', 'STUDENT');