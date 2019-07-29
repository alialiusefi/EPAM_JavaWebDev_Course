use petshelter;

select adoptions_made.id,adoptions_made.pet_id, adoptions_made.adoption_start, adoptions_made.adoption_end,adoptions_made.user_id from adoptions_made
  inner join pets on (adoptions_made.pet_id = pets.id) where pets.name like ? limit ?,?;




select adoptions_made.id,adoptions_made.pet_id, adoptions_made.adoption_start,
       adoptions_made.adoption_end,adoptions_made.user_id
from adoptions_made where
  ((adoption_start between ? and ?) or (adoption_end between ? and ?)) and user_id = ?
limit ?,?;



select count(adoptions_made.pet_id) from adoptions_made where pet_id = ? and (
  (adoptions_made.adoption_start <= ? and adoption_end >= ?)
    or (adoptions_made.adoption_start <= ? and adoption_start >= ?)
    or (adoptions_made.adoption_end >= ? and adoption_end <= ?)
    or (adoptions_made.adoption_start >= ? and adoption_start <= ?)
    or (adoption_end is null and adoption_start <= ?));


/*add these queries to properties and create dao, service, add them in jsp
select pets.id, pets.name, pets.photo, pets.dateofbirth,
       pets.weight, pets.date_sheltered,pets.shelter_id,
       pets.breed_id, pets.status
       from pets inner join adoptions_made on pets.id = pet_id
where adoptions_made.pet_id = ? limit ?,?;

select userinfo.user_id, userinfo.email, userinfo.firstname, userinfo.lastname,
       userinfo.dateofbirth, userinfo.address, userinfo.phone
from userinfo inner join adoptions_made
  on userinfo.user_id = adoptions_made.user_id
where adoptions_made.user_id = ? limit ?,?;
*/
/*not null*/


/*for null end and negative*/
select count(adoptions_made.pet_id) from adoptions_made where pet_id = ? and (
  adoption_end >= ? or adoption_start >= ? or
  (adoption_end is null and adoption_start <= ?)
  );



select count(adoptions_made.pet_id)
from adoptions_made where pet_id = ? and (
  adoption_end is null and adoption_start >= ?
  );

/*negative*/
select count(petshelter.adoptions_made.pet_id)
from adoptions_made where pet_id = ? and (
  (adoption_start <= ?/*end*/AND adoption_end >= ? /*start*/)
  OR (adoption_start >= ? /*start*/AND adoption_start <= ?/*start*/ AND adoption_end <= ? /*start*/)
  OR (adoption_end <=  ? /*start*/ AND adoption_end >= ? /*end*/AND adoption_start <= ? /*end*/)
  OR (adoption_start >= ? /*end*/AND adoption_start <= ? /*start*/)
  );

select count(petshelter.adoptions_made.pet_id)
from adoptions_made where pet_id = ? and (
  (? <= adoption_end AND ? >= adoption_start)
  );

select pets.id, pets.name, pets.photo, pets.dateofbirth, pets.weight, pets.date_sheltered, pets.shelter_id, pets.breed_id,pets.status from pets where pets.shelter_id = ? and pets.status = ? limit ?,?;

select pets.id, pets.name, pets.photo, pets.dateofbirth, pets.weight, pets.date_sheltered, pets.shelter_id, pets.breed_id,pets.status from pets where pets.status = 'Sheltered' limit ?,?;


select count(pets.id) from pets;

select shelters.id, shelters.name, shelters.location from shelters;

select count(users.role) from users inner join userinfo on (userinfo.user_id = users.id and users.role = 1) where userinfo.phone like ?;


select userinfo.user_id from userinfo inner join users on users.role = 1;

select users.id,users.username,users.password,users.role from users where users.role=1 limit ?,? ;


select users.id,users.username,users.password,users.role from users limit ?,?;


select count(users.role) from users where users.role = 1;


select userinfo.user_id,
       users.username,
       users.password,
       userinfo.firstname,
       userinfo.lastname,
       userinfo.dateofbirth,
       userinfo.address,
       userinfo.phone
from userinfo inner join users on userinfo.user_id = users.id;



select pets.id, pets.name, pets.photourl, pets.dateofbirth, pets.weight, pets.date_sheltered, pets.shelter_id, pets.breed_id,pets.status from pets;

insert into pets (id, name, photourl, dateofbirth, weight, date_sheltered, shelter_id, breed_id, status) values (?,?,?,?,?,?,?,?,?);

update pets set pets.id = ? , pets.name = ? , pets.photourl = ?, pets.dateofbirth = ?, pets.weight = ?, pets.date_sheltered = ? , pets.shelter_id = ?, pets.breed_id = ?, pets.status = ?;


select * from adoptions_made;
insert into adoptions_made (pet_id, adoptionStart, adoptionEnd, user_id)
values (1,'2001-04-01',null,1);
delete from adoptions_made where adoptions_made.pet_id = ? and adoptions_made.adoptionStart = ? and adoptionEnd = ? and adoptions_made.user_id = ?;

/*deleting tables*/
alter table users drop foreign key users;
alter table adoptions_made drop foreign key fk_adoptions_userid;
alter table userinfo drop primary key;
drop table users;
drop table userinfo;

select userinfo.id,
       userinfo.firstname,
       userinfo.lastname,
       userinfo.dateofbirth,
       userinfo.address,
       userinfo.phone
from userinfo where userinfo.id = ?;

select * from userinfo;

