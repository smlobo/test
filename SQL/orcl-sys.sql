--describe user_sys_privs;

--select * from book_list;
--insert into book_list (NAME, RATING, date_added, application) values 
  --('The SPARC Architecture Manual', 6, '23-MAR-18', 'Oracle SQL Developer');
  
--select * from cat;

--describe user_tables;
--select table_name, tablespace_name from user_tables;
--select * from user_tables where table_name = 'BOOK_LIST';

--select * from user_sys_privs;

--SELECT table_name, owner, tablespace_name FROM all_tables;
--SELECT table_name, owner, tablespace_name FROM all_tables where table_name = 'BOOK_LIST';

--SELECT owner, table_name FROM dba_tables;

--BEGIN
-- DVSYS.CONFIGURE_DV (
--   dvowner_uname         => 'sys',
--   dvacctmgr_uname       => 'sys');
--END;

GRANT CREATE SESSION TO dbv_owner IDENTIFIED BY oracle;
GRANT CREATE SESSION TO dbv_owner_backup IDENTIFIED BY oracle;
GRANT CREATE SESSION TO dbv_acctmgr IDENTIFIED BY oracle;
GRANT CREATE SESSION TO dbv_acctmgr_backup IDENTIFIED BY oracle;