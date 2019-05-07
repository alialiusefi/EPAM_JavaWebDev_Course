CREATE DATABASE if not exists petshelter DEFAULT CHAR SET utf8;

CREATE USER if not exists 'petshelter_admin'@'%' IDENTIFIED BY 'adminpass';

grant select,insert,update,delete,alter,drop
on petshelter.*
  to 'petshelter_admin'@'%'
  IDENTIFIED by 'adminpass';

CREATE USER if not exists 'petshelter_staff'@'%' IDENTIFIED BY 'staffpass';

grant select,insert,update,delete
  on petshelter.adoptions_made
  to 'petshelter_staff'@'%'
  IDENTIFIED by 'staffpass';

grant select,insert,update,delete
  on petshelter.breeds
  to 'petshelter_staff'@'%'
  IDENTIFIED by 'staffpass';

grant select,insert,update,delete
  on petshelter.pets
  to 'petshelter_staff'@'%'
  IDENTIFIED by 'staffpass';

grant select
  on petshelter.shelters
  to 'petshelter_staff'@'%'
  IDENTIFIED by 'staffpass';

grant select,insert,update
  on petshelter.userinfo
  to 'petshelter_staff'@'%'
  IDENTIFIED by 'staffpass';

CREATE USER if not exists 'petshelter_guest'@'%' IDENTIFIED BY 'guestpass';

grant select,update
  on petshelter.pets
  to 'petshelter_guest'@'%'
  IDENTIFIED by 'guestpass';

grant insert,update
  on petshelter.adoptions_made
  to 'petshelter_guest'@'%'
  IDENTIFIED by 'guestpass';

grant select
  on petshelter.shelters
  to 'petshelter_guest'@'%'
  IDENTIFIED by 'guestpass';

grant select,insert,update
  on petshelter.users
  to 'petshelter_guest'@'%'
  IDENTIFIED by 'guestpass';

grant select,insert,update
  on petshelter.userinfo
  to 'petshelter_guest'@'%'
  IDENTIFIED by 'guestpass';