
SELECT 
    MAX(`id`)
INTO @game_number FROM
    game;
    set transaction isolation level read uncommitted;
update game
set `title` = "Non Repeatable Read 2"
where `id`= @game_number;
commit;