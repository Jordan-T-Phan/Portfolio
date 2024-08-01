CREATE DEFINER=`root`@`localhost` TRIGGER `game_library_BEFORE_INSERT` BEFORE INSERT ON `game_library` FOR EACH ROW BEGIN
declare old_balance int;

declare item_price int;
declare item_percent int;
set old_balance = 0;

set item_price = 0;
set item_percent = 0;

SELECT 
    balance
INTO old_balance FROM
    account_detail
WHERE
    `id` = new.acc_id;

SELECT 
    orig_price, salePercent
INTO item_price , item_percent FROM
    game
WHERE
    `id` = new.game_id;

if (old_balance < item_price*item_percent)then
begin
SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'Error Message';
				
end;

end if;
UPDATE `game_store`.`account_detail` 
SET 
    `balance` = old_balance - (item_price * item_percent)
WHERE
    `id` = new.acc_id;


END