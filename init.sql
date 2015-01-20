drop database deposit;
create database deposit;
use deposit;
create table client ( 
id_client int NOT NULL AUTO_INCREMENT,
name varchar(50), 
address varchar(50),
number int,
PRIMARY KEY (id_client) 
); 

create table account ( 
id int NOT NULL AUTO_INCREMENT, 
bank varchar(20) NOT NULL, 
open_date DATE NOT NULL,
close_date DATE,
balance FLOAT NOT NULL,
currency ENUM('EUR', 'USD', 'UAH') NOT NULL,
client_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (client_id) REFERENCES client(id_client)
); 

create table operation ( 
id int NOT NULL AUTO_INCREMENT, 
account_id int NOT NULL, 
date_time TIMESTAMP NOT NULL,
sum FLOAT NOT NULL,
action ENUM('close', 'refill') NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (account_id) REFERENCES account(id)
); 

create table account_info ( 
account_id int NOT NULL, 
duration int NOT NULL,
interest_rate FLOAT NOT NULL,
periodicity int NOT NULL,
PRIMARY KEY (account_id),
FOREIGN KEY (account_id) REFERENCES account(id)
);

INSERT INTO client (name, address, number)
VALUES ('Andrey Zavizionov','Slinko 7','0664215881');

INSERT INTO client (name, address, number)
VALUES ('Evgeniy Volnov','Pushkina-Kolotushkina','0664232181');

INSERT INTO client (name, address, number)
VALUES ('Albert Einstein','Austria','0664235421');

INSERT INTO client (name, address, number)
VALUES ('Albert Hofmann','Somwhere, 19', '000');

INSERT INTO client (name, address, number)
VALUES ('Satan','Hell','0666666666');

INSERT INTO account (bank, open_date, close_date, balance, currency, client_id)
VALUES ('Privat','01.03.12','01.05.14', '1500', 'USD', '3');

INSERT INTO account (bank, open_date, close_date, balance, currency, client_id)
VALUES ('VBR','01.04.11','01.02.13', '3500', 'UAH', '1');

INSERT INTO account (bank, open_date,  balance, currency, client_id)
VALUES ('Privat','01.02.13', '2500', 'USD', '2');

INSERT INTO account (bank, open_date, balance, currency, client_id)
VALUES ('Privat','04.02.13', '1000', 'EUR', '5');

INSERT INTO account (bank, open_date, close_date, balance, currency, client_id)
VALUES ('Mercury Bank','01.09.14','01.12.14', '500', 'USD', '5');

INSERT INTO operation (account_id, date_time, sum, action)
VALUES ('1','01.09.14 10:10:24','500', 'refill');

INSERT INTO operation (account_id, date_time, sum, action)
VALUES ('2','01.04.13 15:11:21','300', 'refill');

INSERT INTO operation (account_id, date_time, sum, action)
VALUES ('5','01.12.14 10:10:24','1000', 'close');

INSERT INTO operation (account_id, date_time, sum, action)
VALUES ('4','02.01.12 09:10:10','100', 'refill');

INSERT INTO operation (account_id, date_time, sum, action)
VALUES ('2','01.02.13 16:10:24','500', 'close');

INSERT INTO account_info (account_id, duration, interest_rate, periodicity)
VALUES ('1','28','18.5', '3');

INSERT INTO account_info (account_id, duration, interest_rate, periodicity)
VALUES ('2','24','21', '12');

INSERT INTO account_info (account_id, duration, interest_rate, periodicity)
VALUES ('3','12','17.6', '1');

INSERT INTO account_info (account_id, duration, interest_rate, periodicity)
VALUES ('4','36','19', '12');

INSERT INTO account_info (account_id, duration, interest_rate, periodicity)
VALUES ('5','6','17', '3');
INSERT INTO account_info (account_id, duration, interest_rate, periodicity)
VALUES ('6','6','17', '3');

SELECT table_schema "Data Base Name", sum( data_length + index_length ) / 1024 / 1024 "Data Base Size in MB" 
FROM information_schema.TABLES GROUP BY table_schema ; 

select name, sum(balance) from 
account right join client 
ON account.client_id = client.id_client
group by name;

select name, date_time, action, sum
from client inner join 
(select sum, client_id, date_time, action
from operation inner join account 
on account.id = operation.account_id) as a
on client.id_client = a.client_id
order by name;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT ALL ON deposit.* TO 'admin'@'localhost';
GRANT SELECT ON deposit.* TO 'user'@'localhost';


create table roles ( 
id int NOT NULL AUTO_INCREMENT, 
name varchar(20) NOT NULL,
PRIMARY KEY (id)
); 

create table users ( 
id int NOT NULL AUTO_INCREMENT, 
login varchar(20) NOT NULL, 
password varchar(20) NOT NULL,
role_id int NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (role_id) REFERENCES roles(id)
); 


INSERT INTO roles (name)
VALUES ('admin');

INSERT INTO roles (name)
VALUES ('user');

INSERT INTO users (login, password,  role_id)
VALUES ('admin','admin', '1');

INSERT INTO users (login, password,  role_id)
VALUES ('user','user', '2');
