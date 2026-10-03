/*
Let N be the number of CITY entries in STATION, and let N' be the number of 
distinct CITY names in STATION; query the value of N - N' from STATION. In 
other words, find the difference between the total number of CITY entries in 
the table and the number of distinct CITY entries in the table.
*/

use test;

CREATE TABLE IF NOT EXISTS station (
	id int auto_increment primary key,
	city varchar(21),
	state varchar(2),
	lat_n float(9, 6),
	long_w float(9, 6)
);

-- insert some sample data
insert into station(city, state, lat_n, long_w)  values
	('Raleigh', 'NC', 35.8323, 78.6439),
	("Atlanta", "GA", 33.7626, 84.4228),
	("Phoenix", "AZ", 33.5722, 112.0891),
	("Las Vegas", "NV", 36.2291, 115.2607),
	("Atlanta", "TX", 33.1136, 94.1672),
	("Omaha", "NE", 41.2628, 96.0495),
	("Indianapolis", "IN", 39.7771, 86.1458),
	('Raleigh', 'MS', 32.0322, 89.5247),
	('San Francisco', 'CA', 37.7562, 122.4430);

-- select data from station table
select * from station order by long_w;
set @n = (select count(city) from station);
set @nprime = (select count(distinct city) from station);
select @n - @nprime;

DROP TABLE IF EXISTS station;