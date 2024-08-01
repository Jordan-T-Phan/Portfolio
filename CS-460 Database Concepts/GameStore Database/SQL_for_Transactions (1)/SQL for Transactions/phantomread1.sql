set transaction isolation level read uncommitted;
start transaction;
select * into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/sd1.txt' from game where `id` > 98;
do sleep(10);
select * into outfile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/sd2.txt' from game where `id` > 98;
commit