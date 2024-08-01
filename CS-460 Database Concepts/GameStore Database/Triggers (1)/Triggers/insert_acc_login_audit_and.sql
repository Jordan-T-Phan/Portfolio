CREATE DEFINER=`root`@`localhost` TRIGGER `insert_acc_login_audit_And_insert_acc_detail_null` AFTER INSERT ON `account_login` FOR EACH ROW begin
INSERT INTO `game_store`.`account_detail`
(`id`
)
VALUES
(new.id);

INSERT INTO `game_store`.`acc_login_audit`
(
`acc_id`,
`usernameAfter`,
`passwordAfter`,
`modified_user`,
`modified_date`,
`action`)
select
new.id,new.username,new.password,current_user(),current_timestamp(),'I';
end