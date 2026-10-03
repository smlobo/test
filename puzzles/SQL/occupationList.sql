/*

Generate the following two result sets:

1. Query an alphabetically ordered list of all names in OCCUPATIONS, immediately 
followed by the first letter of each profession as a parenthetical (i.e.: 
enclosed in parentheses). For example: AnActorName(A), ADoctorName(D), 
AProfessorName(P), and ASingerName(S).

2. Query the number of ocurrences of each occupation in OCCUPATIONS. Sort the 
occurrences in ascending order, and output them in the following format: 
	There are a total of [occupation_count] [occupation]s.
where [occupation_count] is the number of occurrences of an occupation in 
OCCUPATIONS and [occupation] is the lowercase occupation name. If more than one 
Occupation has the same [occupation_count], they should be ordered 
alphabetically.

Note: There will be at least two entries in the table for each type of 
occupation.

*/

use test;

CREATE TABLE IF NOT EXISTS occupations (
	id int auto_increment primary key,
	name varchar(100),
	occupation varchar(100)
);

-- insert some sample data
insert into occupations(name, occupation) values
	('Samantha', 'Doctor'),
	('Julia', 'Actor'),
	('Maria', 'Actor'),
	('Meera', 'Singer'),
	('Ashely', 'Professor'),
	('Ketty', 'Professor'),
	('Christine', 'Professor'),
	('Jane', 'Actor'),
	('Jenny', 'Doctor'),
	('Priya', 'Singer');

-- select data from occupations table
/*select name, 'yeehaw', 
	replace(occupation, 'Doctor', '(D)') 
	from occupations;
select name, concat('(', substr(occupation, 1, 1), ')')
	from occupations;*/
select concat(name, '(', substr(occupation, 1, 1), ')')
	from occupations
	order by name;
/*select occupation, count(occupation) as total 
	from occupations 
	group by occupation
	order by total, occupation;*/
select concat('There are a total of ', count(occupation), ' ', 
	lcase(occupation), 's.') 
	from occupations 
	group by occupation
	order by count(occupation), occupation;

DROP TABLE IF EXISTS occupations;