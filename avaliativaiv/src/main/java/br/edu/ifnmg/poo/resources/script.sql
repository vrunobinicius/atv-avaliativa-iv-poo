create database alphasystem;
use alphasystem;

create table role (
ID bigint unsigned PRIMARY KEY AUTO_INCREMENT,
name varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table user (
ID bigint unsigned AUTO_INCREMENT,
role_ID bigint unsigned,
name varchar(150) NOT NULL,
email varchar(255) NOT NULL,
birthdate DATE NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY(role_ID) REFERENCES role(ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table credential (
ID bigint unsigned PRIMARY KEY AUTO_INCREMENT, 
user_ID bigint unsigned,
username varchar(15) NOT NULL,
password varchar(32) NOT NULL,
lastAccess DATE,
enabled tinyint(1) NOT NULL,
FOREIGN KEY(user_ID) REFERENCES user(ID) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table reader (
ID bigint unsigned PRIMARY KEY AUTO_INCREMENT,
name varchar(150) NOT NULL,
email varchar(255) NOT NULL,
birthdate DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table librarian (
ID bigint unsigned PRIMARY KEY AUTO_INCREMENT,
name varchar(150) NOT NULL,
email varchar(255) NOT NULL,
birthdate DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

desc role;
desc user;
desc credential;
desc reader;
desc librarian;