	CREATE TABLE admins (
    id INT IDENTITY(1,1) NOT NULL,
    adminLogin VARCHAR(50),
	adminPassword VARCHAR(50),
	adminFirstName VARCHAR(25),
	adminSurname VARCHAR(50),
	adminEmail VARCHAR(100),
	salt VARCHAR(50),
	PRIMARY KEY (id),
	)