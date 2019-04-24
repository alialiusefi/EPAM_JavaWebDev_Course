use petshelter;
select users.user_id,users.username,users.password,users.role from users where user_id = ?;