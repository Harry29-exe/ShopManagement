INSERT INTO users(username, password_hash, name, surname)
VALUES
-- passw 123 bcrypt 10 rounds
('bob', '$2a$10$3rMk61g9T59tP.y.obdwCeijVgtGosCMngmiD7wmO96qPAEQ7t9YS', 'Bob', 'Smith'),
-- passw 1234 bcrypt 10 rounds
('alice', '$2a$10$4OQ3D6RvpmYIlfJ489nqdOLgIJ9Jrr.VDskpAYTdmg4rbLdbc9uS.', 'Alice', 'Bush')
;

INSERT INTO user_roles(user_id, role)
VALUES (1, 0),
       (1, 1),
       (1, 2),
       (1, 3);