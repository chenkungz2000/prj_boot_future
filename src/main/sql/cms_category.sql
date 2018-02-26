SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS cms_category;




/* Create Tables */

CREATE TABLE cms_category
(
	ID int NOT NULL AUTO_INCREMENT,
	-- 父id
	parent_id int COMMENT '父id',
	type int,
	name varchar(25),
	keyword varchar(25),
	crdate datetime,
	updte datetime,
	PRIMARY KEY (ID),
	UNIQUE (ID)
);



