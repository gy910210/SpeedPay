/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/8/5 18:28:29                            */
/*==============================================================*/



drop database if exists SpeedPay;

create database SpeedPay default character set utf8;

use SpeedPay;

drop table if exists admin;

drop table if exists borrowProof;

drop table if exists consumptionProof;

drop table if exists goods;

drop table if exists goods_content;

drop table if exists message;

drop table if exists purchaseContent;

drop table if exists renewalApply;

drop table if exists repayProof;

drop table if exists transferProof;

drop table if exists user;

drop table if exists withdrawProof;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   admin_account        varchar(20) not null,
   admin_password       varchar(15) not null,
   primary key (admin_account)
);

/*==============================================================*/
/* Table: borrowProof                                           */
/*==============================================================*/
create table borrowProof
(
   borrowProof_id       int not null auto_increment,
   borrowProof_borrowUserId int,
   borrowProof_repayUserId int,
   borrowProof_borrowTime date not null,
   borrowProof_sum      double not null,
   borrowProof_repayTime date not null,
   borrowProof_isRepayed int not null,
   primary key (borrowProof_id)
);

/*==============================================================*/
/* Table: consumptionProof                                      */
/*==============================================================*/
create table consumptionProof
(
   consumptionProof_id  int not null auto_increment,
   consumptionProof_shopId int,
   consumptionProof_userId int,
   consumptionProof_time date not null,
   consumptionProof_sum double not null,
   consumptionProof_cause varchar(50),
   primary key (consumptionProof_id)
);

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   goods_id             int not null auto_increment,
   goods_name           varchar(50) not null,
   goods_price          double not null,
   goods_barCode	varchar(20) not null,
   primary key (goods_id)
);

/*==============================================================*/
/* Table: goods_content                                         */
/*==============================================================*/
create table goods_content
(
   goods_content_id     int not null auto_increment,
   goods_content_purchaseContentId int,
   goods_content_goodsId int,
   goods_content_quantity int not null,
   primary key (goods_content_id)
);

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   message_id           int not null auto_increment,
   message_userId       int,
   message_content      varchar(500) not null,
   message_type         int not null,
   primary key (message_id)
);

/*==============================================================*/
/* Table: purchaseContent                                       */
/*==============================================================*/
create table purchaseContent
(
   purchaseContent_id   int not null auto_increment,
   purchaseContent_marketId int,
   purchaseContent_userId int,
   purchaseContent_consumptionSum double not null,
   purchaseContent_consumptionTime date not null,
   primary key (purchaseContent_id)
);

/*==============================================================*/
/* Table: renewalApply                                          */
/*==============================================================*/
create table renewalApply
(
   renewalApply_id      int not null auto_increment,
   renewalApply_borrowProofId int,
   renewalApply_time    date not null,
   renewalApply_isChecked int not null,
   primary key (renewalApply_id)
);

/*==============================================================*/
/* Table: repayProof                                            */
/*==============================================================*/
create table repayProof
(
   repayProof_id        int not null auto_increment,
   repayProof_borrowProofId int,
   repayProof_sum       double not null,
   repayProof_trueRepayTime date not null,
   repayProof_isOnTime  int not null,
   primary key (repayProof_id)
);

/*==============================================================*/
/* Table: transferProof                                         */
/*==============================================================*/
create table transferProof
(
   transferProof_id     int not null auto_increment,
   transferProof_transferUserId int,
   transferProof_receiverUserId int,
   transferProof_time   date not null,
   transferProof_sum    double not null,
   transferProof_cause  varchar(50),
   primary key (transferProof_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              int not null auto_increment,
   user_name            varchar(30) not null,
   user_phoneNum        varchar(15) not null,
   user_password        varchar(12) not null,
   user_bankcardNum     varchar(20),
   user_IDNum           varchar(20) not null,
   user_isChecked       int not null,
   user_balance         double not null,
   user_type            int not null,
   user_level           int not null,
   user_payPassword     varchar(6) not null,
   primary key (user_id)
);

/*==============================================================*/
/* Table: withdrawProof                                         */
/*==============================================================*/
create table withdrawProof
(
   withdrawProof_id     int not null auto_increment,
   withdrawProof_userId int,
   withdrawProof_atmID  varchar(15) not null,
   withdrawProof_time   date not null,
   withdrawProof_sum    double not null,
   primary key (withdrawProof_id)
);


/*==============================================================*/
/* Table: qrCode                                                */
/*==============================================================*/
create table qrCode
(
   qrCode_id     int not null auto_increment,
   qrCode_content   varchar(1000) not null,
   qrCode_status   int not null,
   primary key (qrCode_id)
);

alter table borrowProof add constraint FK_Relationship_12 foreign key (borrowProof_borrowUserId)
      references user (user_id) on delete restrict on update restrict;

alter table borrowProof add constraint FK_Relationship_13 foreign key (borrowProof_repayUserId)
      references user (user_id) on delete restrict on update restrict;

alter table consumptionProof add constraint FK_Relationship_8 foreign key (consumptionProof_shopId)
      references user (user_id) on delete restrict on update restrict;

alter table consumptionProof add constraint FK_Relationship_9 foreign key (consumptionProof_userId)
      references user (user_id) on delete restrict on update restrict;

alter table goods_content add constraint FK_Relationship_20 foreign key (goods_content_purchaseContentId)
      references purchaseContent (purchaseContent_id) on delete restrict on update restrict;

alter table goods_content add constraint FK_Relationship_21 foreign key (goods_content_goodsId)
      references goods (goods_id) on delete restrict on update restrict;

alter table message add constraint FK_Relationship_15 foreign key (message_userId)
      references user (user_id) on delete restrict on update restrict;

alter table purchaseContent add constraint FK_Relationship_16 foreign key (purchaseContent_marketId)
      references user (user_id) on delete restrict on update restrict;

alter table purchaseContent add constraint FK_Relationship_17 foreign key (purchaseContent_userId)
      references user (user_id) on delete restrict on update restrict;

alter table renewalApply add constraint FK_Relationship_14 foreign key (renewalApply_borrowProofId)
      references borrowProof (borrowProof_id) on delete restrict on update restrict;

alter table repayProof add constraint FK_Relationship_11 foreign key (repayProof_borrowProofId)
      references borrowProof (borrowProof_id) on delete restrict on update restrict;

alter table transferProof add constraint FK_Relationship_18 foreign key (transferProof_transferUserId)
      references user (user_id) on delete restrict on update restrict;

alter table transferProof add constraint FK_Relationship_19 foreign key (transferProof_receiverUserId)
      references user (user_id) on delete restrict on update restrict;

alter table withdrawProof add constraint FK_Relationship_10 foreign key (withdrawProof_userId)
      references user (user_id) on delete restrict on update restrict;

