#удалить таблицу
drop table if exists vacancy;

#создать таблицу
create table vacancy (
  id varchar(32) not null,
  vacancyname varchar(300) not null,
  vacancysalary varchar(32) not null,
  vacancyexperience varchar(32) not null,
  vacancyarea varchar(32) not null,
   url varchar(100) not null,
  primary key (id)
);