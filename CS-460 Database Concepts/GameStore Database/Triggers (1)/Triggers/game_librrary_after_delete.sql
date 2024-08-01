CREATE DEFINER=`root`@`localhost` TRIGGER `game_library_AFTER_DELETE` AFTER DELETE ON `game_library` FOR EACH ROW BEGIN

declare price_game int;
declare percent_sale int;
declare old_balance int;

set price_game =0;
set percent_sale =0;
set old_balance=0;

SELECT 
    orig_price, salePercent
INTO price_game , percent_sale FROM
    game
WHERE
    `id` = old.game_id;
SELECT 
    balance
INTO old_balance FROM
    account_detail
WHERE
    `id` = old.acc_id;
if old.hours_played < 2 then
begin
UPDATE `game_store`.`account_detail` 
SET 
    `balance` = old_balance + (price_game * percent_sale)
WHERE
    `id` = old.acc_id;

end;
end if;


END