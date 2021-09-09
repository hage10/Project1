Requirement:
----------------
-> JRE installed
-> SQL Server 2017 or higher, contain a table with
   required fields to storage the CVE List information
----------------
In case your database does not contain a table, you can
execute example query below:

CREATE TABLE CVEList(
Id varchar(max) NOT NULL,
Sta varchar(max) NOT NULL,
Des varchar(max) NOT NULL,
Ref varchar(max),
Phase varchar(max),
Votes varchar(max),
Comments varchar(max)
)