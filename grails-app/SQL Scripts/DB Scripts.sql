// MySql Connect

mysql --host=localhost --user='startu60_serrano' --password='oGo-S84-RLR-ogf' vivany


// Dump database

mysqldump -u 'startu60_serrano' --password='oGo-S84-RLR-ogf' --databases 'vivany' > '/Users/proyectoweb/Documents/RSN/grails_apps/vivanyServer/grails-app/SQL Scripts/DBbackup.sql'

// Export table to CSV

select * from tablename   INTO OUTFILE  '/tmp/filename.csv'  FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';


// Create tb_data_icd Table

CREATE TABLE IF NOT EXISTS `tb_data_icd` (
`col1` int(1) DEFAULT NULL,
`col2` varchar(1) DEFAULT NULL,
`col3` varchar(1) DEFAULT NULL,
`col4` int(2) DEFAULT NULL,
`col5` varchar(3) DEFAULT NULL,
`col6` varchar(6) DEFAULT NULL,
`col7` varchar(5) DEFAULT NULL,
`col8` varchar(4) DEFAULT NULL,
`col9` varchar(185) DEFAULT NULL,
`col10` varchar(5) DEFAULT NULL,
`col11` varchar(5) DEFAULT NULL,
`col12` varchar(5) DEFAULT NULL,
`col13` varchar(5) DEFAULT NULL,
`col14` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

// Load info from delimited file

load data local infile '/Users/proyectoweb/Downloads/icd102010enMeta/codes.txt'
into table tb_data_icd
fields terminated by ';'
enclosed by ';'
lines terminated by '\n'
(col1,col2,col3,col4,col5,col6,col7,col8,col9,col10,col11,col12,col13,col14);


// Copy info into disease table

INSERT INTO disease (version, disease_id, disease_name)  
SELECT '0',col8, col9
  FROM `tb_data_icd`
 WHERE `col2` = 'T'