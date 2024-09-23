/*
 Navicat Premium Dump SQL

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44-log)
 Source Host           : localhost:3306
 Source Schema         : demo2023

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44-log)
 File Encoding         : 65001

 Date: 23/09/2024 17:41:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for health
-- ----------------------------
DROP TABLE IF EXISTS `health`;
CREATE TABLE `health`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1837912283708284931 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of health
-- ----------------------------
INSERT INTO `health` VALUES (1, '202006595301', '2024-04-23', 'http://www.ldfnbhyl.gov/l49rrfu0vt');
INSERT INTO `health` VALUES (2, '202006595302', '2024-08-12', 'http://www.nuzffili.edu/w9v0rgln8i');
INSERT INTO `health` VALUES (3, '202006595303', '2024-06-24', 'http://www.ahoodrtx.net/f86jo24phm');
INSERT INTO `health` VALUES (4, '202006595304', '2024-04-03', 'http://www.ffsvtmdi.edu/z5at7vmqf9');
INSERT INTO `health` VALUES (5, '202006595305', '2024-09-14', 'http://www.kizkbtts.edu/o99lw7sd4w');
INSERT INTO `health` VALUES (6, '202006595306', '2024-04-18', 'http://www.adkliyvg.gov/i3dx5nruzw');
INSERT INTO `health` VALUES (7, '202006595307', '2024-11-13', 'http://www.ddyjwjgi.com/rt37fv6rzr');
INSERT INTO `health` VALUES (8, '202006595308', '2024-09-20', 'http://www.geztzsbo.gov/6kj0o7fadd');
INSERT INTO `health` VALUES (9, '202006595309', '2024-01-16', 'http://www.mdroazmt.gov/wahy12scfy');
INSERT INTO `health` VALUES (10, '202006595310', '2024-09-09', 'http://www.ptweyely.net/vyq5maqbyi');
INSERT INTO `health` VALUES (11, '202006595311', '2024-06-21', 'http://www.lamprium.com/6pc19dek1e');
INSERT INTO `health` VALUES (12, '202006595312', '2024-05-14', 'http://www.annlorii.gov/t1ungkiljy');
INSERT INTO `health` VALUES (13, '202006595313', '2024-05-03', 'http://www.klmziupj.gov/ywcjn8vhu8');
INSERT INTO `health` VALUES (14, '202006595314', '2024-11-14', 'http://www.rbjmpkam.org/v336c4gfxs');
INSERT INTO `health` VALUES (15, '202006595315', '2024-05-11', 'http://www.oyqamzdt.org/gg2m7b6hos');
INSERT INTO `health` VALUES (16, '202006595316', '2024-01-14', 'http://www.iuripdkh.com/5v6hyn4lni');
INSERT INTO `health` VALUES (17, '202006595317', '2024-03-26', 'http://www.cwtkyjph.com/rs3b6yzby1');
INSERT INTO `health` VALUES (18, '202006595318', '2024-08-22', 'http://www.dgjfytlt.com/lsoynm5fnj');
INSERT INTO `health` VALUES (19, '202006595319', '2024-11-09', 'http://www.piccnnzb.org/e5m6rzng1x');
INSERT INTO `health` VALUES (20, '202006595320', '2024-12-16', 'http://www.owwetnjh.net/telv5v374r');
INSERT INTO `health` VALUES (21, '202006595321', '2024-07-02', 'http://www.utlpvkst.org/cqbrvyma9h');
INSERT INTO `health` VALUES (22, '202006595322', '2024-06-15', 'http://www.zwbsxsfx.gov/3qk9wresjc');
INSERT INTO `health` VALUES (23, '202006595323', '2024-04-13', 'http://www.ybzjqexo.net/obsfkd2t8h');
INSERT INTO `health` VALUES (24, '202006595324', '2024-10-16', 'http://www.beccgugh.net/5yxdc0v1nj');
INSERT INTO `health` VALUES (25, '202006595325', '2024-01-05', 'http://www.afdaczyt.net/gu8b4as98v');
INSERT INTO `health` VALUES (26, '202006595326', '2024-06-09', 'http://www.vutrgznw.net/5gi7jy9ytd');
INSERT INTO `health` VALUES (27, '202006595327', '2024-09-29', 'http://www.ggqcxdau.com/9qiykbdyfn');
INSERT INTO `health` VALUES (28, '202006595328', '2024-04-23', 'http://www.kidgvotg.edu/yk1ptsvpri');
INSERT INTO `health` VALUES (29, '202006595329', '2024-09-02', 'http://www.daqzdjkr.com/vljst1z2zu');
INSERT INTO `health` VALUES (30, '202006595330', '2024-06-20', 'http://www.mpsxlgam.gov/xo4lss4lx7');
INSERT INTO `health` VALUES (31, '202006595331', '2024-04-12', 'http://www.ouprvhtn.edu/22mqe4q8ay');
INSERT INTO `health` VALUES (32, '202006595332', '2024-05-29', 'http://www.yoxlvtho.com/6tqyhd92py');
INSERT INTO `health` VALUES (33, '202006595333', '2024-05-23', 'http://www.rqyygmtc.edu/9yvpyc7pay');
INSERT INTO `health` VALUES (34, '202006595334', '2024-11-21', 'http://www.xpletfoj.edu/rb3kq4rk1j');
INSERT INTO `health` VALUES (35, '202006595335', '2024-11-19', 'http://www.pfacbqmi.org/36m5lllo18');
INSERT INTO `health` VALUES (36, '202006595336', '2024-12-01', 'http://www.vrppssph.com/6ca48f33d3');
INSERT INTO `health` VALUES (37, '202006595337', '2024-05-02', 'http://www.mlfnxetn.edu/h5b4d5csk4');
INSERT INTO `health` VALUES (38, '202006595338', '2024-03-02', 'http://www.plyxtnbv.edu/0re2ljxajo');
INSERT INTO `health` VALUES (39, '202006595339', '2024-05-27', 'http://www.wbgdtkig.edu/ywk7yvuuua');
INSERT INTO `health` VALUES (40, '202006595340', '2024-05-09', 'http://www.mxvejaod.com/5buo0rqrit');
INSERT INTO `health` VALUES (47, '2023306906', '2024-09-17', 'www.baidu.com');
INSERT INTO `health` VALUES (65, '202020202020', '2024-09-11', 'asdad');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(32) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '邹菊华', 15);
INSERT INTO `student` VALUES (2, '盛明辉', 18);
INSERT INTO `student` VALUES (4, '李涛', 16);
INSERT INTO `student` VALUES (5, '吴佩珂', 35);
INSERT INTO `student` VALUES (6, '樊越川', 15);
INSERT INTO `student` VALUES (7, '唐洁', 18);
INSERT INTO `student` VALUES (8, '周思丽', 27);
INSERT INTO `student` VALUES (9, '余钍胜', 16);
INSERT INTO `student` VALUES (10, '张小丽', 35);
INSERT INTO `student` VALUES (11, '阳超', 15);
INSERT INTO `student` VALUES (12, '高芸菲', 18);
INSERT INTO `student` VALUES (13, '吴双珏', 27);
INSERT INTO `student` VALUES (14, '倪君', 16);
INSERT INTO `student` VALUES (15, '王小丫', 35);
INSERT INTO `student` VALUES (16, '陈州', 15);
INSERT INTO `student` VALUES (17, '李玉琪', 18);
INSERT INTO `student` VALUES (18, '莫晓琪', 27);
INSERT INTO `student` VALUES (19, '黄鑫睿', 16);
INSERT INTO `student` VALUES (20, '王志', 35);
INSERT INTO `student` VALUES (21, '林申奥', 15);
INSERT INTO `student` VALUES (22, '黄坤鹏', 18);
INSERT INTO `student` VALUES (23, '何天', 27);
INSERT INTO `student` VALUES (24, '黄奕尧', 16);
INSERT INTO `student` VALUES (25, '刘荣荣', 35);
INSERT INTO `student` VALUES (26, '郑慈芳', 15);
INSERT INTO `student` VALUES (27, '李闯', 18);
INSERT INTO `student` VALUES (28, '马江林', 27);
INSERT INTO `student` VALUES (29, '梁龙艺', 16);
INSERT INTO `student` VALUES (30, '李鑫宇', 35);
INSERT INTO `student` VALUES (31, '杨宇', 15);
INSERT INTO `student` VALUES (32, '徐传宝', 18);
INSERT INTO `student` VALUES (33, '周茂', 27);
INSERT INTO `student` VALUES (34, '骆宇', 16);
INSERT INTO `student` VALUES (35, '王浩宇', 35);
INSERT INTO `student` VALUES (45, '陈浩平', 35);

SET FOREIGN_KEY_CHECKS = 1;
