CREATE SCHEMA `thisisjava` DEFAULT CHARACTER SET utf8 ;

create table users (
userid varchar(50) primary key,
username varchar(50) not null,
userpassword varchar(50) not null,
userage numeric(3) not null,
useremail varchar(50) not null
);