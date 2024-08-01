
SELECT 
    MAX(`id`) into @game_number 
FROM 
    game;
set transaction isolation level read uncommitted;
SELECT 
    * into outfile  'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/sd2.txt'
FROM
    game
WHERE
    `id` = @game_number