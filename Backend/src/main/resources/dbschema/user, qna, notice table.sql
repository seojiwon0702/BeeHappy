use happyhouse;

-- -----------------------------------------------------
-- Table `happyhouse`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`user` ;
CREATE TABLE IF NOT EXISTS `happyhouse`.`user` (
  `userid` VARCHAR(16) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `userpwd` VARCHAR(16) NOT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `address` VARCHAR(100) DEFAULT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `auth` boolean default false,
  PRIMARY KEY (`userid`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 
  COLLATE = utf8mb4_0900_ai_ci;
  
  INSERT INTO `user` VALUES ('admin','관리자','admin',NULL,NULL,now(),true),
  ('ssafy','싸피','ssafy','ssafy@ssafy.com','서울특별시 강남구',now(),false);

select * from user;
-- delete from user where userid = "admin";

-- -----------------------------------------------------
-- Table `happyhouse`.`qnaboard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`qnaboard` ;
CREATE TABLE IF NOT EXISTS `happyhouse`.`qnaboard` (
  `no` INT NOT NULL AUTO_INCREMENT,				#글번호
  `writer` VARCHAR(16) NULL DEFAULT NULL, 		#작성자
  `subject` VARCHAR(100) NULL DEFAULT NULL,		#제목
  `content` VARCHAR(500) NULL DEFAULT NULL,		#내용
--   `hit` INT NULL DEFAULT 0,					#조회수
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,	#작성일
  PRIMARY KEY (`no`),
  INDEX `qnaboard_to_user_fk` (`writer` ASC) VISIBLE,
  CONSTRAINT `qnaboard_to_user_fk` FOREIGN KEY (`writer`) REFERENCES `happyhouse`.`user` (`userid`) on delete cascade on update cascade)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into qnaboard(writer, subject, content) 
values('ssafy', 'Vue Axios 문의', 'Vue를 이용한 HTTP 통신 어떻게 하나요?'), 
      ('ssafy', '뷰와 스프링부트를 이용한 실전 프로젝트 관련 문의드립니다', '프로젝트를 직접만드는 내용.'),
      ('admin', '프론트엔드 프레임워크 관련 문의드립니다', 'Vue는 프론트엔드의 인기있는 프레임워크 입니다.');

SELECT * FROM happyhouse.qnaboard;

-- -----------------------------------------------------
-- Table `happyhouse`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `happyhouse`.`notice` ;
CREATE TABLE IF NOT EXISTS `happyhouse`.`notice` (
  `no` INT NOT NULL AUTO_INCREMENT,				#글번호
  `writer` VARCHAR(16) NULL DEFAULT NULL, 		#작성자
  `subject` VARCHAR(100) NULL DEFAULT NULL,		#제목
  `content` VARCHAR(500) NULL DEFAULT NULL,		#내용
--   `hit` INT NULL DEFAULT 0,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,	#작성일
  PRIMARY KEY (`no`),
  INDEX `qnaboard_to_user_fk` (`writer` ASC) VISIBLE,
  CONSTRAINT `notice_to_user_fk` FOREIGN KEY (`writer`) REFERENCES `happyhouse`.`user` (`userid`) on delete cascade on update cascade)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into notice(writer, subject, content) 
values('admin', '뷰와 스프링부트를 이용한 실전 프로젝트', '프로젝트를 직접만드는 내용.');
      
SELECT * FROM happyhouse.notice;

-- Dump completed on 2021-05-25 21:01:38

  