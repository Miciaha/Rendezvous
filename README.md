# Rendezvous
Scenario:
You are working for a software company that has been contracted to develop a GUI-based scheduling desktop application. The contract is with a global consulting organization that conducts business in multiple languages and has main offices in Phoenix, Arizona; White Plains, New York; Montreal, Canada; and London, England. The consulting organization has provided a MySQL database that the application must pull data from. The database is used for other systems, so its structure cannot be modified.

The organization outlined specific business requirements that must be met as part of the application. From these requirements, a system analyst at your company created solution statements for you to implement in developing the application. These statements are listed in the requirements section.

Your company acquires Country and First-Level-Division data from a third party that is updated once per year. These tables are prepopulated with read-only data. Please use the attachment “Locale Codes for Region and Language” to review division data. Your company also supplies a list of contacts, which are prepopulated in the Contacts table; however, administrative functions such as adding users are beyond the scope of the application and done by your company’s IT support staff. Your application should be organized logically using one or more design patterns and generously commented using Javadoc so your code can be read and maintained by other programmers.

## A. Create a GUI-based application using no external libraries (excluding JavaFX and MySQL JDBC)

### 1. Create a log-in form

| Status              | Task                                                                                                   |
|---------------------|--------------------------------------------------------------------------------------------------------|
| :heavy_check_mark:  | Accepts a user ID and password and provides an appropriate error message                               |
| :heavy_check_mark:  | Determines the user’s location and displays it in a label on the log-in form                           |
| :heavy_check_mark:  | Displays the log-in form in English or French based on the user’s computer language setting            |
| :heavy_check_mark:  | Translates error control messages into English or French based on the user’s computer language setting |

### 2. Customers

| Status              | Task                                                                           |
|---------------------|--------------------------------------------------------------------------------|
| :heavy_check_mark:  | Customer records can be added, updated, and deleted                            |
| :heavy_check_mark:  | Collect customer name, address, postal code, and phone number                  |
| :heavy_check_mark:  | Country and first-level division data is pre-populated in separate combo boxes |
| :heavy_check_mark:  | All of the original customer information is displayed on the update form       |
| :heavy_check_mark:  | All of the fields can be updated except for Customer_ID                        |
| :heavy_check_mark:  | Customer data is displayed using a TableView                                   |
| :heavy_check_mark:  | Message is displayed in the user interface when a customer is deleted          |

### 3. Appointments

| Status              | Task                                                                       |
|---------------------|----------------------------------------------------------------------------|
| :heavy_check_mark:  | Appointments can be added, updated, and deleted                            |
| :heavy_check_mark:  | Contact name is assigned to an appointment using a drop-down menu          |
| :heavy_check_mark:  | Custom message is displayed in the user interface when canceled (deleted)  |
| :heavy_check_mark:  | Appointment_ID is auto-generated and disabled throughout the application   |
| :heavy_check_mark:  | Appointment information is displayed on the update form in local time zone |
| :heavy_check_mark:  | Show user upcoming appointment upon login                                  |
| :heavy_check_mark:  | Allow user to view appointment schedules by month and week                 |
| :heavy_check_mark:  | Allow user to adjust appointment times                                     |

### 4. Logical Error Checks

| Status              | Logical Errors                                                                                                 |
|---------------------|----------------------------------------------------------------------------------------------------------------|
| :heavy_check_mark:  | Scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. EST, including weekends |
| :heavy_check_mark:  | Scheduling overlapping appointments for customers                                                              |
| :heavy_check_mark:  | Entering an incorrect username and password                                                                    |

### 5. Reports

| Status              | Description                                                                                                                                                       |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| :heavy_check_mark:  | Total number of customer appointments by type and month                                                                                                           |
| :heavy_check_mark:  | Schedule for each contact in your organization that includes appointment ID, title, type and description, start date and time, end date and time, and customer ID |
| :heavy_check_mark:  | Security report: application entry attempts                                                                                                                       |

### 6. Final Tasks

| Status              | Description                                                                                                                        |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------|
| :heavy_check_mark:  | Write at least two different lambda expressions                                                                                    |
| :heavy_check_mark:  | Record all user log-in attempts, dates, and time stamps and whether each attempt was successful in a file named login_activity.txt |
| :heavy_check_mark:  | Provide Javadoc comments for at least 70 percent of the classes and their members and create an index.html file                    |

### 7. Included in the README.txt file

| Status              | Description                                                        |
|---------------------|--------------------------------------------------------------------|
| :heavy_check_mark:  | Title and purpose of application                                   |
| :heavy_check_mark:  | Author, contact, app version, date                                 |
| :heavy_check_mark:  | IDE including version number                                       |
| :heavy_check_mark:  | Full JDK of version used                                           |
| :heavy_check_mark:  | JavaFX version compatible with JDK version                         |
| :heavy_check_mark:  | Directions for how to run program                                  |
| :heavy_check_mark:  | Description of additional report                                   |
| :heavy_check_mark:  | MySQL Connector driver version number, including the update number |