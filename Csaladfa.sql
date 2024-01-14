CREATE DATABASE Csaladfa
DEFAULT CHARACTER SET utf8
COLLATE utf8_hungarian_ci;


CREATE TABLE Csaladtagok (
    id INT PRIMARY KEY,
    nev VARCHAR(255),
    szuletesi_datum DATE,
    szuletesi_hely VARCHAR(255),
    anya_neve VARCHAR(255),
    apa_neve VARCHAR(255),
    elhalalozas_idopont DATE,
    elhalalozas_helye VARCHAR(255)
);

INSERT INTO Csaladtagok VALUES
(1, 'Biai Tamás', '2004-08-02', 'Pécs', 'Budai Anna', 'Biai Gábor', '2023-01-09', 'München'),
(2, 'Vécsei Bernadett', '1972-05-12', 'Csíkszereda', 'Szöllösi Kata', 'Vécsei Béla', '2022-03-15', 'Kolozsvár'),
(3,'Kun Béla', '1998-06-29', 'Újvidék', 'Eve Manchester','Kun András','2020-08-06', 'Szabadka' ),
(4,'Mohácsiné Varga Petra','1960-12-16','Trencsén', 'Szabó Petra','Varga Tamás', '2021-10-05','Budapest' ),
(5, ' Sára Hermann', '1945-11-25', 'Muraszombat', 'Kolozsvári Andrea', 'Sára Endre', '2014-02-05'),
(6, ' König Ottó', '1932-07-12', 'Varga', 'Mecseki Piroska', 'König Ödön','2006-03-19' 'Zágráb'),
(7,'Dugonovics Tihamér', '1928-09-29', 'Vajdahunyad', 'Molnár Erzsébet','Dugonovics Tivadar','1998-04-08','Budapest'),
(8,'Lévai Réka','2006-10-04','Szekszárd','Rezes Judit', 'Lévai Károly','2016-05-09','Pécs' ),
(9, 'Szendrei Tamara', '1997-02-02', 'Fiume', 'Kandó Beatrix', 'Szendrei Sándor', '2023-12-31','Budapest'),
(10, 'Csengei Emese', '1912-09-12', 'Győr', 'Lendvai Eszter', 'Csengei Ferenc','2022-10-19', 'Amszterdam'),
(11,'Tordai Klára', '1894-01-29', 'Budapest', 'Kovács Mária','Tordai Elemér','1985-12-20','New York'),
(12,'Kiss Mária', '1989-04-30', 'Békéscsaba', 'Károlyi Mónika', 'Kiss Tibor','2009-07-29','Tokió' );


UPDATE Csaladtagok
SET szuletesi_datum = '1987-02-20'
WHERE nev = 'Biai Tamás';