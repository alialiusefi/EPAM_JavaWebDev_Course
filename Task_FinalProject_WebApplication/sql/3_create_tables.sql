/*
    nchar and nvarchar can store Unicode characters.
    char and varchar cannot store Unicode characters.
    char and nchar are fixed-length which will reserve storage space
          for number of characters you specify even if you don't use
          up all that space.
    varchar and nvarchar are variable-length which will only use up
    spaces for the characters you store. It will not reserve storage
    like char or nchar.

 */
use petshelter;
create table if not exists users
(
  id       integer    not null auto_increment unique,
  username nchar(16)  not null unique,
  password nchar(32)  not null,
  role     tinyint(1) not null,
  primary key (id)
) default charset utf8;

use petshelter;
create table if not exists breeds
(
  id          integer      not null unique auto_increment,
  name        nvarchar(32) not null,
  description nvarchar(255),
  origin      nvarchar(48) not null,
  PRIMARY KEY (id)
) default charset utf8;

use petshelter;
create table if not exists shelters
(
  id       integer      not null unique auto_increment,
  name     nvarchar(32) not null unique,
  location nvarchar(32) not null,
  PRIMARY KEY (id)
) default charset utf8;

use petshelter;
create table if not exists pets
(
  id             integer                             not null auto_increment,
  name           nvarchar(32)                        not null,
  photourl       nvarchar(255)                       not null,
  dateofbirth    date                                not null,
  weight         double                              not null,
  date_sheltered date                                not null,
  shelter_id     integer                             not null,
  breed_id       integer,
  status         enum ('Adopted','Sheltered','Dead') not null,
  PRIMARY KEY (id),
  constraint fk_shelterid foreign key (shelter_id) references shelters (id),
  constraint fk_breedid foreign key (breed_id) references breeds (id)
) default charset utf8;

use petshelter;
create table if not exists adoptions_made
(
  pet_id         integer not null,
  adoption_start date    not null,
  adoption_end   date,
  user_id        integer not null,
  constraint fk_petid foreign key (pet_id) references pets (id)
    on delete cascade,
  constraint fk_adoptions_userid foreign key (user_id) references users (id)
    on delete restrict
) default charset utf8;


use petshelter;
create table if not exists userinfo
(
  user_id     integer       not null unique,
  email       nvarchar(254) not null,
  firstname   nvarchar(16)  not null,
  lastname    nvarchar(16)  not null,
  dateofbirth date          not null,
  address     nvarchar(100) not null,
  phone       bigint(20)    not null,
  constraint fk_users_userid FOREIGN KEY (user_id) references users (id)
    on delete cascade
) default charset utf8;


/*
use petshelter;
create table if not exists vaccinations_made
(
  pet_id           integer not null,
  vaccination_id   integer not null,
  date_vaccination date    not null,
  FOREIGN KEY (pet_id) references pets (id),
  FOREIGN KEY (vaccination_id) references vaccinations (id)
) default charset utf8;

*/

/*
use petshelter;
create table if not exists vaccinations
(
  id   integer      not null auto_increment unique,
  name nvarchar(32) not null,
  constraint PK_vaccinations primary key (id)
) default charset utf8;
*/




