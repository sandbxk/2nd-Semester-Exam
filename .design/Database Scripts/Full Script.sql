USE [master]
GO

DROP DATABASE CSe21A_29_SOSU_Sims
GO

CREATE DATABASE CSe21A_29_SOSU_Sims
GO

USE CSe21A_29_SOSU_Sims
GO

CREATE TABLE school (
	id INT IDENTITY(1,1) NOT NULL,
	schoolName VARCHAR(50)
	)

CREATE TABLE teachers (
    id INT IDENTITY(1,1) NOT NULL,
    teacherLogin VARCHAR(50),
	teacherPassword VARCHAR(50),
	teacherFirstName VARCHAR(25),
	teacherSurname VARCHAR(50),
	teacherEmail VARCHAR(100),
	salt VARCHAR(50),
	school VARCHAR(50),
	PRIMARY KEY (id),
	FOREIGN KEY (school) REFERENCES school (id) 
	)
	
	CREATE TABLE students (
    id INT IDENTITY(1,1) NOT NULL,
    teacherLogin VARCHAR(50),
	teacherPassword VARCHAR(50),
	teacherFirstName VARCHAR(25),
	teacherSurname VARCHAR(50),
	teacherEmail VARCHAR(100),
	salt VARCHAR(50),
	school VARCHAR(50),
	PRIMARY KEY (id),
	FOREIGN KEY (school) REFERENCES school (id) 
	)
	
	CREATE TABLE classes (
    id INT IDENTITY(1,1) NOT NULL,
	className VARCHAR(50),
	teacherID int,
	school VARCHAR(50),
	PRIMARY KEY (id),
	FOREIGN KEY (school) REFERENCES school (id),
	FOREIGN KEY (teacherID) REFERENCES teachers (id) 
	)

CREATE TABLE cityName (
    zipCode VARCHAR(255) UNIQUE,
    cityName VARCHAR (85),
    PRIMARY KEY (zipCode)
	)