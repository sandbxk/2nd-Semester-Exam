USE [master]
GO

DROP DATABASE CSe21A_29_SOSU_Sims
GO

CREATE DATABASE CSe21A_29_SOSU_Sims
GO

USE CSe21A_29_SOSU_Sims
GO

CREATE TABLE schools (
	schoolId INT IDENTITY(1,1) NOT NULL,
	schoolName VARCHAR(50),
	schoolZipCode int,
	PRIMARY KEY (schoolID),
	FOREIGN KEY (schoolZipCode) REFERENCES zipCode (zipCode)
	)

	CREATE TABLE accounts (
    accountId INT IDENTITY(1,1) NOT NULL,
    login VARCHAR(50),
	password VARCHAR(50),
	firstName VARCHAR(25),
	surname VARCHAR(50),
	email VARCHAR(100),
	school int,
	auth int NOT NULL,
	PRIMARY KEY (accountId),
	FOREIGN KEY (school) REFERENCES schools (schoolId) 
	)
	
	CREATE TABLE classes (
    classesId INT IDENTITY(1,1) NOT NULL,
	className VARCHAR(50),
	teacherID int,
	school int,
	PRIMARY KEY (id),
	FOREIGN KEY (school) REFERENCES schools (schoolId),
	FOREIGN KEY (teacherID) REFERENCES accounts (accountId) 
	)

CREATE TABLE zipCodes (
    zipCode int UNIQUE,
    cityName NVARCHAR (85),
    PRIMARY KEY (zipCode)
	)