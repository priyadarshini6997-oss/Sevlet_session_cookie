create database userdb;
use userdb;

create table users(
id int primary key auto_increment,
username varchar(50) unique not null,
password varchar(50) not null
);

insert into users(username,password) values('priya','12345');
insert into users(username,password) values('amit','password');
insert into users(username,password) values('john','john123');

select * from users;