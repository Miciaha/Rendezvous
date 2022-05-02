Title: Rendezvous (a.k.a. QAM2 Task 1: Java Application Development)
This application tracks customer appointments for our user, "Test."
I decided to name the application Rendezvous for a few reasons:
    1. This is an appointment scheduling application and "rendezvous" is synonymous with "appointment."
    2. The word rendezvous is of french origin which happens to be one of the language translation requirements.
    3. Naming an application, even for a class project, adds a layer of realism that pushes me to make my own work better.
    (I hope doing so is not against the grading rubric.)

The full scenario can be found here: https://github.com/Miciaha/Rendezvous

Created by: Miciaha Ivey (mivey37@wgu.edu)
Date: 4/29/2022
App Version: 1.0.0

IDE:    IntelliJ IDEA 2021.3.3 (Ultimate Edition)
JavaFX: 18 by JavaFX runtime of version 11.0.2
JDK:    openjdk-17 java version 17.0.2
MySQL:  mssql-jdbc-10.2.0.jre17

The additional report contains the date, time, username, and status of previous login attempts.

Running the program:
    - Download the directory to your computer.
    - Open your IDE of choice and ensure that "Scheduling Application" is selected for the Build and Run process
    - This project requires the use of SQL Server; ensure that it is up, running, and accepting connections.
        There is a JDBC driver included under the "Ext Libraries" folder in the main directory along with SQL scripts in the base directory to build the database on your behalf.
        If you face any additional issues here, this reference may help: https://docs.microsoft.com/en-us/sql/connect/jdbc/connecting-to-sql-server-with-the-jdbc-driver?view=sql-server-ver15
    - The connection to the database from the application can be found in the project under utilities.database as SQLDBConnection.
        - Modify the connection string to reflect your database connection properties (i.e. databaseName, user, and password fields)
    - Login Credentials: {Username="Test", Password="Test"}

If there are any issues, please reach out to me using the email above, or via GitHub.