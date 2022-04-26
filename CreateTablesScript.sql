CREATE DATABASE rendezvous;

USE rendezvous;

CREATE TABLE COUNTRIES (
	Country_ID INT NOT NULL PRIMARY KEY,
	Country VARCHAR(50),
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update DATETIME,
	Last_Updated_By VARCHAR(50)
);

CREATE TABLE [FIRST-LEVEL DIVISIONS] (
	Division_ID INT NOT NULL PRIMARY KEY,
	Division VARCHAR(50),
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update DATETIME,
	Last_Updated_By VARCHAR(50),
	Country_ID INT 
	FOREIGN KEY (Country_ID) REFERENCES COUNTRIES(Country_ID)
								ON DELETE CASCADE
);

CREATE TABLE CUSTOMERS (
	Customer_ID INT NOT NULL PRIMARY KEY,
	Customer_Name VARCHAR(50),
	Address VARCHAR(100),
	Postal_Code VARCHAR(50),
	Phone VARCHAR(50),
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update DATETIME,
	Last_Updated_By VARCHAR(50),
	Division_ID INT
	FOREIGN KEY (Division_ID) REFERENCES [FIRST-LEVEL DIVISIONS](Division_ID)
);

CREATE TABLE USERS (
	[User_ID]       INT NOT NULL PRIMARY KEY,
	[User_Name]     VARCHAR(50) UNIQUE,
	[Password]      varchar(max),
	Create_Date     DATETIME,
	Created_By      VARCHAR(50),
	Last_Update     DATETIME,
	Last_Updated_By VARCHAR(50)
);

CREATE TABLE CONTACTS (
	Contact_ID INT NOT NULL PRIMARY KEY,
	Contact_Name VARCHAR(50),
	Email VARCHAR(50)
);

CREATE TABLE APPOINTMENTS (
	Appointment_ID INT NOT NULL PRIMARY KEY,
	Title VARCHAR(50),
	Description VARCHAR(50),
	Location VARCHAR(50),
	Type VARCHAR(50),
	Start DATETIME,
	[End] DATETIME,
	Create_Date DATETIME,
	Created_By VARCHAR(50),
	Last_Update DATETIME,
	Last_Updated_By VARCHAR(50),
	Customer_ID INT,
	[User_ID] INT,
	Contact_ID INT
	FOREIGN KEY (Customer_ID) REFERENCES CUSTOMERS(Customer_ID)
											ON DELETE CASCADE,
	FOREIGN KEY ([User_ID]) REFERENCES USERS([User_ID]),
	FOREIGN KEY (Contact_ID) REFERENCES CONTACTS(Contact_ID)
);
