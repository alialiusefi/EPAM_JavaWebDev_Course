use petshelter;

select userinfo.id,
       userinfo.firstname,
       userinfo.lastname,
       userinfo.dateofbirth,
       userinfo.address,
       userinfo.phone
from userinfo where userinfo.id = ?;

select * from userinfo;

