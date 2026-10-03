/*
Write a query that prints a list of employee names (i.e.: the name attribute) 
from the Employee table in alphabetical order.
*/

use test;

CREATE TABLE IF NOT EXISTS employee (
	employee_id int primary key,
	name varchar(25),
	months int,
	salary int 
);

-- insert some sample data
insert into employee values 
	(12228, 'Rose', 15, 1968),
	(73454, 'Angela', 2, 3443),
	(97634, 'Frank', 11, 1608),
	(56244, 'Patrick', 8, 1345),
	(35133, 'Lisa', 3, 2222),
	(76244, 'Toma', 22, 3333),
	(14234, 'Joe', 6, 7777);

-- select data from employee table
select name from employee order by name;

DROP TABLE IF EXISTS employee;