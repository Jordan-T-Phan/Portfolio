CREATE DEFINER=`root`@`localhost` TRIGGER `mod_library_AFTER_DELETE` AFTER DELETE ON `mod_library` FOR EACH ROW BEGIN
declare price_mod int;
declare old_balance int;
set price_mod =0;
set old_balance=0;

SELECT 
    price
INTO price_mod FROM
    game_store.mod
WHERE
    id = old.mod_id;
SELECT 
    balance
INTO old_balance FROM
    account_detail
WHERE
    id = old.acc_id;
if old.hours_played < 2 then
begin
UPDATE game_store.account_detail 
SET 
    balance = old_balance + (price_mod)
WHERE
    id = old.acc_id;

end;
end if;


END