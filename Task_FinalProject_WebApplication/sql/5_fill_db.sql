INSERT INTO shelters (name, location)
VALUES ('Manhattan Shelter', 'New York'),
       ('Scranton Main Shelter', 'Scranton');

INSERT INTO breeds (name, description, origin)
VALUES ('German Shepherd',
        'Big Ears and Strong',
        'Germany'),
       ('Chihuahua', 'Small, Weak and Nimble', 'Mexico');

INSERT INTO pets (id,name,photourl,dateofbirth,weight,date_sheltered,shelter_id,breed_id,status)
values (1, 'John', 'photos\\1.jpg', '2000-06-06', 10.2, '2015-09-09', 1, 1, 'Sheltered'),
       (2, 'Chichi', 'photos\\2.jpg', '2019-01-03', 2.1, '2019-01-06', 2, 2, 'Adopted');


insert into users (username, password, role)
VALUES ('Johnny11', 'd6d8b3550f38c81fadb4e463f121604b', 0),
       ('Maria22', 'b10a8db164e0754105b7a99be72e3fe5', 2);

insert into userinfo (user_id, email, firstname, lastname, dateofbirth, address, phone)
values (1, 'micheal1@gmail.com', 'Michael', 'Scott', '1999-09-09', 'TempsVille 21, 98193 Scranton,USA', 154175431010),
       (2, 'maria112@gmail.com', 'Maria', 'Vale', '1999-08-01', 'TempsVille 11, 92193 Scranton,USA', 154175441010);

insert into adoptions_made (pet_id, adoption_start, adoption_end, user_id)
VALUES (1, '2020-09-02', null, 1),
       (1, '2019-01-10', '2019-02-02', 2);

