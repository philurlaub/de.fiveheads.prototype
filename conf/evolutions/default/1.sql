# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table article (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  content                   TEXT,
  publication_date          datetime,
  language                  varchar(255),
  constraint pk_article primary key (id))
;

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  points                    bigint,
  constraint pk_account primary key (email))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table article;

drop table account;

SET FOREIGN_KEY_CHECKS=1;

