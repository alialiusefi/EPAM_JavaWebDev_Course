CREATE DATABASE if not exists petshelter DEFAULT character set utf8;

CREATE USER if not exists 'petshelter_admin'@'%' IDENTIFIED BY 'adminpass';

grant select,insert,update,delete
on petshelter.*
  to 'petshelter_admin'@'%'
  IDENTIFIED by 'adminpass';

grant select,insert,update,delete
  on petshelter.*
  to 'petshelter_admin'@'localhost'
  IDENTIFIED by 'adminpass';
