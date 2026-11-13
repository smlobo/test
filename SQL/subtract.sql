/* count distinct tables */

use test;

CREATE TABLE IF NOT EXISTS demos(
	id int auto_increment primary key,
	val int 
);

-- insert some sample data
INSERT INTO demos(val)
VALUES(1),(1),(2),(2),(NULL),(3),(4),(NULL),(5);

-- select data from demos table
/*SELECT * FROM demos;*/

set @var1 := (select count(val) from demos);
set @var2 := (select count(distinct val) from demos);
select @var1 - @var2;

DROP TABLE IF EXISTS demos;