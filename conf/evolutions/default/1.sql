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

create table rating (
  id                        bigint auto_increment not null,
  score                     bigint,
  user_email                varchar(255),
  article_id                bigint,
  constraint pk_rating primary key (id))
;

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  points                    bigint,
  role                      varchar(255),
  constraint pk_account primary key (email))
;

alter table rating add constraint fk_rating_user_1 foreign key (user_email) references account (email) on delete restrict on update restrict;
create index ix_rating_user_1 on rating (user_email);
alter table rating add constraint fk_rating_article_2 foreign key (article_id) references article (id) on delete restrict on update restrict;
create index ix_rating_article_2 on rating (article_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table article;

drop table rating;

drop table account;

SET FOREIGN_KEY_CHECKS=1;

