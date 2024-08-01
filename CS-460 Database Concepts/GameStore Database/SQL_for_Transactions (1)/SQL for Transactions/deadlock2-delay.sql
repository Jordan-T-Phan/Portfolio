SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT 
    MAX(`id`)
INTO @game_number_max FROM
    game;
    SELECT 
    MAX(`id`)
INTO @mod_number_max FROM
    `mod`;
do sleep(2);

set autocommit = 0;
start transaction;
select price from `mod` where `id`=@mod_number_max for share;
update game set orig_price = orig_price+1 where `id`=@game_number_max;
do sleep(2);

SET SESSION TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT 
    MAX(`id`)
INTO @game_number_max FROM
    game;
    SELECT 
    MAX(`id`)
INTO @mod_number_max FROM
    `mod`;
set autocommit = 0;
start transaction;
select price from `mod` where `id`=@mod_number_max for share;
update game set orig_price = orig_price+1 where `id`=@game_number_max;
do sleep(5);

commit;