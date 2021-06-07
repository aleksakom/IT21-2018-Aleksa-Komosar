INSERT INTO "smer"
VALUES (1, 'Inzenjerstvo informacionih sistema', 'IT');
INSERT INTO "smer"
VALUES (2, 'Informacioni inzenjering', 'IN');
INSERT INTO "smer"
VALUES (3, 'Softversko inzenjerstvo i informacione tehnologije', 'SW');
INSERT INTO "smer"
VALUES (4, 'Racunarstvo i automatika', 'RA');
INSERT INTO "smer"
VALUES (5, 'Inzenjerski menadzment', 'IM');
INSERT INTO "smer"
VALUES (6, 'Industrijsko inzenjerstvo', 'II');

INSERT INTO "projekat"
VALUES (1, 'MobiShop', 'WOTIS', 'Izrada veb sajtova koriscenjem MV 5, BootStrap, C# - Visual Studio. Potrebno je realizovati osnovne CRUD operacije. Takodje, potrebno je realizovati uloge korisnika i administratora, kako bi se uocili razliciti pristupi.');
INSERT INTO "projekat"
VALUES (2, 'MobiShop-2', 'ANDR', 'Realizacija mobilne aplikacije za Android uredjaje. Potrebno je realizovati osnovne mogucnosti logovanja i registracije. Od tehnologija se koriste Android Studio i Firebase.');
INSERT INTO "projekat"
VALUES (3, 'HeartString', 'MEN1', 'Unapredjenje postojece striming platforme dodavanjem socijalne komponente. Korisnici aplikacije imaju mogucnost umrezavanja sa ljudima istog ili slicnog muzickog ukusa.');
INSERT INTO "projekat"
VALUES (4, 'Naucna konferencija', 'PBP', 'Kreiranje baze podata IS Naucne konferencije FTN-a. Pored razumevanja procesa organizovanja dogadja, potrebno je izraditi semu cele baze podataka, ogranicenja, kao i relacionu semu BP.');
INSERT INTO "projekat"
VALUES (5, 'Rea', 'RCH', 'Razvoj chatbot-a koji omogucava na osnovu svoje baze znanja da komunicira sa korisnicima sistema. ');

INSERT INTO "grupa"
VALUES (1,'II',1);
INSERT INTO "grupa"
VALUES (2,'I',2);
INSERT INTO "grupa"
VALUES (3,'III',1);
INSERT INTO "grupa"
VALUES (4,'IV',4);
INSERT INTO "grupa"
VALUES (5,'V',5);

INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (1, 'Aleksa','Komosar', '21/2018', 1, 4);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (2, 'Sara','Kijanovic', '14/2018', 1, 1);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (3, 'Petar','Petrovic', '5/2015', 2, 2);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (4, 'Ana','Anic', '154/2019', 5, 3);
INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat")
VALUES (5, 'Jovan','Jovanovic', '1/2020', 4, 5);

INSERT INTO "smer"
VALUES (-100, 'TestSM', 'TestOZN');

INSERT INTO "projekat"
VALUES (-100, 'TestNaz', 'TestOzn', 'TestOp');

INSERT INTO "grupa"
VALUES (-100,'TestGru',1);

INSERT INTO "student"
VALUES (-100, 'TestAleksa','TestKomosar', 'Test21/2018', 1, 4);

