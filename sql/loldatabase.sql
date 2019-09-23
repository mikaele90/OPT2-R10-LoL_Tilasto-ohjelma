DROP DATABASE IF EXISTS LOLDATABASE;
CREATE DATABASE LOLDATABASE;

USE LOLDATABASE;


CREATE TABLE Profile

(
    Id INT NOT NULL,
    Name VARCHAR(50) NOT NULL,
    Psw VARCHAR(25) NOT NULL,

    PRIMARY KEY (Id)
);



CREATE TABLE GameData

(
    Champion VARCHAR(50) NOT NULL,
    Kills INT NOT NULL,
    Deaths INT NOT NULL,
    Assist INT NOT NULL,
    GameId INT NOT NULL,
    WinLose VARCHAR(10) NOT NULL,
    Positio VARCHAR(20) NOT NULL,
    Gpm INT NOT NULL,
    Id INT NOT NULL,

    PRIMARY KEY (GameId),
    FOREIGN KEY (Id) REFERENCES Profile(Id)
);



CREATE TABLE Item

(
    Slot1 VARCHAR(50) NOT NULL,
    Slot2 VARCHAR(50) NOT NULL,
    Slot3 VARCHAR(50) NOT NULL,
    Slot4 VARCHAR(50) NOT NULL,
    Slot5 VARCHAR(50) NOT NULL,
    Slot6 VARCHAR(50) NOT NULL,
    ItemId INT NOT NULL,
    GameId INT NOT NULL,

    PRIMARY KEY (ItemId),
    FOREIGN KEY (GameId) REFERENCES GameData(GameId)
);


DROP USER IF EXISTS 'ryhma10'@'localhost';
CREATE USER IF NOT EXISTS 'ryhma10'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON loldatabase.* TO 'ryhma10'@'localhost';
