CREATE DATABASE IF NOT EXISTS `assignment-dashboard-1`;

USE `assignment-dashboard-1`;

CREATE TABLE Assignment(
id 			int 				primary key 	auto_increment,
name     	varchar(50) 		not null,
description	varchar(100)		not null
);
