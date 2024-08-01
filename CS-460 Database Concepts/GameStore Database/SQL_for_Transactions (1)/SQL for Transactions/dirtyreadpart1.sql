SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT 
   MAX(`id`) into @game_number
FROM
    game;
select * into outfile  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/sd1.txt' from game where `id` = @game_number;
SET autocommit = 0;
start transaction;
    UPDATE game 
SET 
    title = 'dirty read'
WHERE
    `id` = @game_number;
do sleep(10);
rollback;
commit;

