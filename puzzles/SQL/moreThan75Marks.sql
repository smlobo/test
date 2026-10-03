/*
Query the Name of any student in STUDENTS who scored higher than 75 Marks. 
Order your output by the last three characters of each name. If two or more 
students both have names ending in the same last three characters (i.e.: Bobby, 
Robby, etc.), secondary sort them by ascending ID.
*/

use test;

CREATE TABLE IF NOT EXISTS students (
	id int auto_increment primary key,
	name varchar(25),
	marks int 
);

-- insert some sample data
insert into students(id, name, marks)
	values (55, 'Melvet', 98);
insert into students(id, name, marks)
	values (1, 'Tom', 22);
INSERT INTO students(name, marks)
	VALUES('Ashley', 81),('Jjulia', 88),('Samantha', 75),('Belvet', 84),
		('Julia', 76),
		('Aashley',99);
insert into students(id, name, marks)
	values (66, 'Velvet', 77);
insert into students(id, name, marks)
	values (11, 'Qelvet', 77);

-- select data from students table
select * from students;
SELECT name FROM students where marks > 75 order by substring(name, -3);
SELECT name FROM students where marks > 75 order by substring(name, -3), id;

DROP TABLE IF EXISTS students;