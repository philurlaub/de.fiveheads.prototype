# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table article (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  type_switch               varchar(255),
  content                   TEXT,
  publication_date          datetime,
  average_score             float,
  classification            varchar(255),
  language                  varchar(255),
  constraint pk_article primary key (id))
;

create table rating (
  id                        bigint auto_increment not null,
  score                     integer,
  user_id                   bigint,
  article_id                bigint,
  rating_timestamp          datetime not null,
  constraint pk_rating primary key (id))
;

create table account (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  points                    bigint,
  role                      varchar(255),
  registration_date         datetime,
  constraint pk_account primary key (id))
;

alter table rating add constraint fk_rating_user_1 foreign key (user_id) references account (id) on delete restrict on update restrict;
create index ix_rating_user_1 on rating (user_id);
alter table rating add constraint fk_rating_article_2 foreign key (article_id) references article (id) on delete restrict on update restrict;
create index ix_rating_article_2 on rating (article_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table article;

drop table rating;

drop table account;

SET FOREIGN_KEY_CHECKS=1;

