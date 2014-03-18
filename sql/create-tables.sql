CREATE TABLE Kayttaja
(
ID serial PRIMARY KEY,
Kayttajanimi varchar(255) UNIQUE,
Password varchar(255),
Etunimi varchar(255),
Sukunimi varchar(255)
);

CREATE TABLE Judoka
(
ID serial PRIMARY KEY,
Etunimi varchar(255) not null,
Sukunimi varchar(255) not null,
Painoluokka varchar(20) not null,
Sukupuoli varchar(10) not null,
Maa varchar(255) not null
);
CREATE TABLE Tekniikka
(
ID serial PRIMARY KEY,
Nimi varchar(255) not null
);

CREATE TABLE Tekniikka_Judoka
(
JudokaID integer REFERENCES Judoka (ID) ON DELETE CASCADE,
TekniikkaID integer REFERENCES Tekniikka (ID) ON DELETE CASCADE
);

CREATE TABLE Kommentti
(
ID serial PRIMARY KEY,
KayttajaID integer not null,
JudokaID integer not null,
Kommentti text not null,
pvm date,
FOREIGN KEY (KayttajaID) references Kayttaja (ID) ON DELETE CASCADE,
FOREIGN KEY (JudokaID) references Judoka (ID) ON DELETE CASCADE
);