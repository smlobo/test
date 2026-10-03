/*
Query the list of CITY names starting with vowels (i.e., a, e, i, o, or u) from 
STATION. Your result cannot contain duplicates.
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
select distinct city from station where city like 'a%' 
    or city like 'e%' 
    or city like 'i%' 
    or city like 'o%' 
    or city like 'u%' 
    or city like 'A%' 
    or city like 'E%' 
    or city like 'I%' 
    or city like 'O%' 
    or city like 'U%';

DROP TABLE IF EXISTS station;