CREATE DEFINER=`root`@`localhost` TRIGGER `mod_library_BEFORE_INSERT` BEFORE INSERT ON `mod_library` FOR EACH ROW BEGIN
declare old_balance int;

declare item_price int;
set old_balance = 0;

set item_price = 0;

SELECT 
    balance
INTO old_balance FROM
    account_detail
WHERE
    id = new.acc_id;

SELECT 
    price
INTO item_price FROM game_store.mod
WHERE
    id = new.mod_id;

if  old_balance < item_price then
begin
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Error Message';

end;

end if;
UPDATE game_store.account_detail 
SET 
    balance = old_balance - (item_price)
WHERE
    id = new.acc_id;


END