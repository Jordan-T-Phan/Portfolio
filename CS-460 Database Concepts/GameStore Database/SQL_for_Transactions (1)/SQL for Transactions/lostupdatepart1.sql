
SELECT 
    MAX(`id`) into @game_number
FROM
    game;
start transaction;
select `title` into @name_game from game where `id` = @game_number;
do sleep(10);
set @name_game = "lost update 1";
update game
set `title` = @name_game where `id` = @game_number;
select @name_game as "title";
commit
