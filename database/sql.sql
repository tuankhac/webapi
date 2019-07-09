CREATE TABLE `menu` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `NAME_EN` varchar(200) DEFAULT NULL,
  `DISPLAY_ORDER` smallint(2) DEFAULT NULL,
  `PICTURE_FILE` varchar(200) DEFAULT NULL,
  `DETAIL_FILE` varchar(200) DEFAULT NULL,
  `DETAIL_FILE_EN` varchar(200) DEFAULT NULL,
  `MENU_LEVEL` smallint(3) DEFAULT NULL,
  `PARENT_ID` smallint(3) DEFAULT NULL,
  `PUBLISH` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `menu_access` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` varchar(50) NOT NULL,
  `MENU_ID` int(9) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX1` (`ROLE_ID`),
  KEY `IDX2` (`MENU_ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6555 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role` (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ROLE_NAME` varchar(200) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `USER_ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `UR_IDX2` (`USER_ID`),
  KEY `UR_IDX1` (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2169 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_info` (
  `USER_ID` varchar(100) NOT NULL,
  `PASSWORD` varchar(500) DEFAULT NULL,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  `LAST_NAME` varchar(200) DEFAULT NULL,
  `MOBILE` varchar(50) DEFAULT NULL,
  `DEPARTMENT` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(70) DEFAULT NULL,
  `GENDER` tinyint(1) DEFAULT NULL,
  `STATUS_ID` tinyint(2) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `MODIFIED_DATE` datetime DEFAULT NULL,
  `ROLE_LEVER` int(2) DEFAULT '0',
  `SALT_PASS` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `USER_INFO_IDX1` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



DELIMITER $$
CREATE DEFINER=`billing`@`localhost` PROCEDURE `layds_menu_theo_nguoidung`(`puser` VARCHAR(20))
    READS SQL DATA
BEGIN
declare exit handler for sqlexception 
	begin
		declare err varchar(500);
		GET DIAGNOSTICS CONDITION 1
		@p1 = RETURNED_SQLSTATE, @p2 = MESSAGE_TEXT;
		set err:=concat('Loi thuc hien, ma loi:',@p1,', ',@p2);
		call access('layds_menu_theo_nguoidung|ERR',err);
	end;
  set @s:=concat('SELECT DISTINCT a.ID, NAME,NAME_EN, DETAIL_FILE, PICTURE_FILE, MENU_LEVEL,
			PARENT_ID,DISPLAY_ORDER FROM menu a,menu_access b,user_role c 
			WHERE PUBLISH = 1 and a.ID = b.MENU_ID AND b.ROLE_ID = 
			c.ROLE_ID AND c.USER_ID=? ORDER BY a.ID,DISPLAY_ORDER,PARENT_ID');
	set @puser = puser;
	PREPARE stmt from  @s;
 EXECUTE stmt using @puser;
 DEALLOCATE PREPARE stmt;
END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`billing`@`localhost` PROCEDURE `search_menu`(
	IN `pUserId` VARCHAR(20), 
    IN `pUserIp` VARCHAR(20)
)
begin
	declare s MEDIUMTEXT ;
    declare exit handler for sqlexception 
	begin
		declare err varchar(500);
		GET DIAGNOSTICS CONDITION 1
		@p1 = RETURNED_SQLSTATE, @p2 = MESSAGE_TEXT;
		set err:=concat('Loi thuc hien, ma loi:',@p1,', ',@p2);
		call access('search_menu|ERR',err);
		SELECT err;
	end;
    
    set s:='select ID,NAME,NAME_EN,DISPLAY_ORDER,PICTURE_FILE,DETAIL_FILE,MENU_LEVEL,PARENT_ID,PUBLISH from menu a where 1=1 and name is not null and name <> \'\'';
    set s:=concat(s,' order by id ');
    set @sql_ = s;
      
       PREPARE stmt from  @sql_;
	EXECUTE stmt ;
 DEALLOCATE PREPARE stmt;
end$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `new_menu`(
	`pid` VARCHAR(200),
    `pname` VARCHAR(200), 
    `pname_en` VARCHAR(200), 
    `pdisplay_order` VARCHAR(5), 
    `ppicture_file` VARCHAR(50), 
    `pdetail_file` VARCHAR(100), 
    `pmenu_level` VARCHAR(5), 
    `pparent_id` VARCHAR(5), 
    `ppublish` VARCHAR(5), 
    `pUserId` VARCHAR(50), 
    `pUserIp` VARCHAR(50)) RETURNS int(11)
begin
	declare s varchar(1000);
    declare exit handler for sqlexception 
	begin
		declare err varchar(500);
		GET DIAGNOSTICS CONDITION 1
		@p1 = RETURNED_SQLSTATE, @p2 = MESSAGE_TEXT;
		set err:=concat('Loi thuc hien, ma loi:',@p1,', ',@p2);
		call access('new_menu|ERR',err);
        return err;
	end;
    
    insert into menu(id,name,name_en,display_order,picture_file,detail_file,menu_level,parent_id,publish)
     values(
		if(nullif(pid,'') is null , null, pid),
        if(nullif(pname,'') is null , null, pname),
        if(nullif(pname_en,'') is null , null, pname_en),
		if(nullif(pdisplay_order,'') is null , null, pdisplay_order),
        if(nullif(ppicture_file,'') is null , null, ppicture_file),
        if(nullif(pdetail_file,'') is null , null, pdetail_file),
        if(nullif(pmenu_level,'') is null , null, pmenu_level),
        if(nullif(pparent_id,'') is null , null, pparent_id),
        if(nullif(ppublish,'') is null , null, ppublish));
    return 1;
end$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`billing`@`localhost` FUNCTION `del_menu`(
	`pId` VARCHAR(20), 
    `pUserId` VARCHAR(50), 
    `pUserIp` VARCHAR(50)) RETURNS int(11)
begin
	declare s varchar(1000);
    declare exit handler for sqlexception 
	begin
		declare err varchar(500);
		GET DIAGNOSTICS CONDITION 1
		@p1 = RETURNED_SQLSTATE, @p2 = MESSAGE_TEXT;
		set err:=concat('Loi thuc hien, ma loi:',@p1,', ',@p2);
		call access('del_menu|ERR',err);
		return err;
	end;
    
	DELETE FROM menu WHERE id = pid;
    return 1;
end$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`billing`@`localhost` FUNCTION `edit_menu`(
	`pid` VARCHAR(20), 
    `pname` VARCHAR(200),
    `pname_en` VARCHAR(200), 
    `pdisplay_order` VARCHAR(5), 
    `ppicture_file` VARCHAR(30), 
    `pdetail_file` VARCHAR(100), 
    `pmenu_level` VARCHAR(5), 
    `pparent_id` VARCHAR(5), 
    `ppublish` VARCHAR(5), 
    `pUserId` VARCHAR(50), 
    `pUserIp` VARCHAR(50)) RETURNS int(11)
begin
	declare s varchar(1000);
    declare exit handler for sqlexception 
	begin
		declare err varchar(500);
		GET DIAGNOSTICS CONDITION 1
		@p1 = RETURNED_SQLSTATE, @p2 = MESSAGE_TEXT;
		set err:=concat('Loi thuc hien, ma loi:',@p1,', ',@p2);
		call access('edit_menu|ERR',err);
		return err;
	end;
    
	UPDATE menu SET 
		name = if(nullif(pname,'') is null , null, pname),
        name_en = if(nullif(pname_en,'') is null , null, pname_en),
        display_order = if(nullif(pdisplay_order,'') is null , null, pdisplay_order),
        picture_file = if(nullif(ppicture_file,'') is null , null, ppicture_file),
        detail_file = if(nullif(pdetail_file,'') is null , null, pdetail_file),
        menu_level = if(nullif(pmenu_level,'') is null , null, pmenu_level),
        parent_id = if(nullif(pparent_id,'') is null , null, parent_id),
        publish = if(nullif(ppublish,'') is null , null, ppublish)
		WHERE id = pid;
    return 1;
end$$
DELIMITER ;
