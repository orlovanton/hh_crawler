drop table if exists vacancy;

create table vacancy (
  id varchar(32) not null,
  vacancyname varchar(300) not null,
  vacancysalary varchar(32) not null,
  vacancyarea varchar(32) not null,
  employer varchar(100) not null,
  url varchar(100) not null,
  primary key (id)
);

insert into vacancy  (id, vacancyname, vacancysalary, vacancyexperience, vacancyarea)
values ("1", "java developer", "10000-300000","нет","спб");
insert into vacancy  (id, vacancyname, vacancysalary, vacancyexperience, vacancyarea)
values ("2", "java developer", "10000-100000","нет","спб");
insert into vacancy  (id, vacancyname, vacancysalary, vacancyexperience, vacancyarea)
values ("3", "java developer", "10000-100000","нет","спб");
insert into vacancy  (id, vacancyname, vacancysalary, vacancyexperience, vacancyarea)
values ("4", "java developer", "10000-100000","нет","спб");