CREATE DEFINER=`root`@`localhost` TRIGGER `update_acc_login_audit` AFTER UPDATE ON `account_login` FOR EACH ROW begin
INSERT INTO `game_store`.`acc_login_audit`
(
`acc_id`,
`usernameBefore`,
`usernameAfter`,
`passwordBefore`,
`passwordAfter`,
`modified_user`,
`modified_date`,
`action`)
select
old.id,old.username,new.username,old.password,new.password,current_user(),current_timestamp(),'U';


end