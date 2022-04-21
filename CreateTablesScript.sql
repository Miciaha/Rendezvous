CREATE DATABASE rendezvous;

USE rendezvous;

CREATE TABLE Country (
	Country_ID INT NOT NULL PRIMARY KEY,
	Country VARCHAR(50),
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update TIMESTAMP,
	Last_Updated_By VARCHAR(50)
);

CREATE TABLE FirstLevelDivision (
	Division_ID INT NOT NULL PRIMARY KEY,
	Division VARCHAR(50),
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update TIMESTAMP,
	Last_Updated_By VARCHAR(50),
	Country_ID INT 
	FOREIGN KEY (Country_ID) REFERENCES Country(Country_ID)
);

CREATE TABLE Customer (
	Customer_ID INT NOT NULL PRIMARY KEY,
	Customer_Name VARCHAR(50),
	"Address" VARCHAR(100),
	Postal_Code VARCHAR(50),
	Phone VARCHAR(50),
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update TIMESTAMP,
	Last_Updated_By VARCHAR(50),
	Division_ID INT
	FOREIGN KEY (Division_ID) REFERENCES FirstLevelDivision(Division_ID)
);

CREATE TABLE "User" (
	"User_ID" INT NOT NULL PRIMARY KEY,
	"User_Name" VARCHAR(50) UNIQUE,
	"Password" TEXT,
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update DATETIME,
	Last_Updated_By VARCHAR(50)
);

CREATE TABLE Contact (
	Contact_ID INT NOT NULL PRIMARY KEY,
	Contact_Name VARCHAR(50),
	Email VARCHAR(50)
);

CREATE TABLE Appointment (
	Appointment_ID INT NOT NULL PRIMARY KEY,
	Title VARCHAR(50),
	Appointment_Description VARCHAR(50),
	Appointment_Location VARCHAR(50),
	Appointment_Type VARCHAR(50),
	Appointment_Start DATETIME,
	Appointment_End DATETIME,
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update TIMESTAMP,
	Last_Updated_By VARCHAR(50),
	Customer_ID INT,
	"User_ID" INT,
	Contact_ID INT
	FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
	FOREIGN KEY ("User_ID") REFERENCES "User"("User_ID"),
	FOREIGN KEY (Contact_ID) REFERENCES Contact(Contact_ID)
);

INSERT INTO "User" VALUES(1, 'mowl', 'mimi**', CURRENT_TIMESTAMP, 'SYSTEM', CURRENT_TIMESTAMP, 'SYSTEM');
