SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS login_user_data;
DROP TABLE IF EXISTS login_user;




/* Create Tables */

CREATE TABLE login_user
(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50),
	password varchar(50),
	phone varchar(11),
	ip varchar(15),
	updatedate datetime,
	createdate datetime,
	PRIMARY KEY (id),
	UNIQUE (id),
	UNIQUE (name),
	UNIQUE (phone)
);


CREATE TABLE login_user_data
(
	id int NOT NULL AUTO_INCREMENT,
	-- 用户id
	login_user_id int COMMENT '用户id',
	-- 登录次数
	login_frequency int COMMENT '登录次数',
	-- 登录ip
	login_ip varchar(15) COMMENT '登录ip',
	updatedate datetime,
	createdate datetime,
	PRIMARY KEY (id),
	UNIQUE (id)
);



/* Create Foreign Keys */

ALTER TABLE login_user_data
	ADD FOREIGN KEY (login_user_id)
	REFERENCES login_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



