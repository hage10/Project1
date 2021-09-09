Yêu cầu phần mềm:
----------------
-> Môi trường JRE
-> SQL Server 2017 hoặc cao hơn, chứa một bảng có
   các trường cần thiết để lưu trữ danh sách CVE
----------------
Trong trường hợp cơ sở dữ liệu của bạn không có sẵn bảng,
bạn có thể sử dụng query mẫu sau:

CREATE TABLE CVEList(
Id varchar(max) NOT NULL,
Sta varchar(max) NOT NULL,
Des varchar(max) NOT NULL,
Ref varchar(max),
Phase varchar(max),
Votes varchar(max),
Comments varchar(max)
)