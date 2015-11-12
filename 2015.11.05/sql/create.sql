create database bookmall character set UTF8;
use database bookmall;

//保存书籍分类
create table category(
	id char(32) primary key,
	name varchar(32),
	des varchar(128)
);

//保存书籍信息的表
create table books(
	id char(32) primary key,
	name varchar(64),
	price numeric(7,2),
	discount numeric(3,2),
	author varchar(32),
	intro text,
	directory text,
	image varchar(64),
	page int,
	publisher varchar(32),
	publishdate char(10),
	stock INT
);

//书籍分类和书籍信息之间多对多的中间表
create table bookcategory(
	bookid char(32) not null,
	categoryid char(32) not null,
	constraint bc primary key(bookid, categoryid),
	constraint bcfk1 foreign key(bookid) references books(id),
	constraint bcfk2 foreign key(categoryid) references category(id)
);

//用户信息表
create table users(
	id char(32) primary key,
	name varchar(32),
	pswd char(32),
	email varchar(64)
);


//订单信息表