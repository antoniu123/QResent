CREATE TABLE tip_user
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR2(20) NOT NULL
);

INSERT INTO tip_user(name) VALUES ('ADMINISTRATOR');
INSERT INTO tip_user(name) VALUES ('TEACHER');
INSERT INTO tip_user(name) VALUES ('STUDENT');

CREATE TABLE applicationUser
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR2(30) NOT NULL,
    password VARCHAR(60) NOT NULL,
    tip_user_id INTEGER NOT NULL,
    student_id INTEGER
);

INSERT INTO applicationUser(username, password, tip_user_id, student_id) values('admin','$2a$10$btXmANN0tim6qqWt3EWZh.sg/VuKlOrO0FsTJp7Iw0idg.0w4RiE2', 1, null);
INSERT INTO applicationUser(username, password, tip_user_id, student_id) values('teacher','$2a$10$650XR88kIqYmv05v6EvYyOZdmZZQMPLU.36B1tuGQSvazXYa8NNvu',2,null);
INSERT INTO applicationUser(username, password, tip_user_id, student_id) values('student','$2a$10$gGMhgUSJ6b.2eCZwhJr/BOp9nEq3d/umX.ntQOkdFLL9Qm.WnBBwy',3,null);

CREATE TABLE student
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    prenume VARCHAR2(20) NOT NULL,
    nume VARCHAR(20) NOT NULL,
    grupa VARCHAR(20) NOT NULL
);

CREATE TABLE materie
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    info VARCHAR2(100) NOT NULL,
    cerinte VARCHAR(100) NOT NULL,
    bonus VARCHAR(100) NOT NULL
);

CREATE TABLE student_materie
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id INTEGER NOT NULL,
    materie_id INTEGER NOT NULL
);

CREATE TABLE student_materie_prezenta
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_materie_id INTEGER NOT NULL,
    data_curs DATE NOT NULL,
    identificator UUID,
    valabilitate DATE,
    prezenta INTEGER
);

CREATE INDEX identificator_id ON student_materie_prezenta(identificator);

CREATE TABLE orar
(
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    materie_id INTEGER NOT NULL,
    data_curs_ora DATE NOT NULL
);

CREATE TABLE parametri
(
    valabilitate_prezenta_minute INTEGER NOT NULL
);

insert into PARAMETRI values(10);

ALTER TABLE applicationUser ADD CONSTRAINT fk_tip_user_id FOREIGN KEY (tip_user_id) REFERENCES tip_user(id);
ALTER TABLE applicationUser ADD CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES student(id);
ALTER TABLE student_materie ADD CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES student(id);
ALTER TABLE student_materie ADD CONSTRAINT fk_materie_id FOREIGN KEY (materie_id) REFERENCES materie(id);
ALTER TABLE student_materie_prezenta ADD CONSTRAINT fk_student_materie_id FOREIGN KEY (student_materie_id) REFERENCES student_materie(id);
ALTER TABLE orar ADD CONSTRAINT fk_orar_materie_id FOREIGN KEY (materie_id) REFERENCES materie(id);

