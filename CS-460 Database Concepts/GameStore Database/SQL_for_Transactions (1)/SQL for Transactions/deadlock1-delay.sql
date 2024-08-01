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

set autocommit =0;
start transaction;
select orig_price from game where `id`=@game_number_max for share;
do sleep(2);

update game set orig_price = orig_price-1 where `id`=@game_number_max;
update `mod` set price = price+1 where `id`=@mod_number_max;
do sleep(2);

commit;
