use petshelter;




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

update pets set pets.id = ? , pets.name = ? , pets.photourl = ?, pets.dateofbirth = ?, pets.weight = ?, pets.date_sheltered = ? , pets.shelter_id = ?, pets.breed_id = ?, pets.status = ?


select * from adoptions_made;
insert into adoptions_made (pet_id, adoption_start, adoption_end, user_id)
values (1,'2001-04-01',null,1);
delete from adoptions_made where adoptions_made.pet_id = ? and adoptions_made.adoption_start = ? and adoption_end = ? and adoptions_made.user_id = ?;

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

