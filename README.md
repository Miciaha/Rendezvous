# Rendezvous

## A. Create a GUI-based application using no external libraries (excluding JavaFX and MySQL JDBC)

### 1. Create a log-in form
| Status              | Task                                                                                                   |
|---------------------|--------------------------------------------------------------------------------------------------------|
| :heavy_check_mark:  | Accepts a user ID and password and provides an appropriate error message                               |
| :heavy_check_mark:  | Determines the user’s location and displays it in a label on the log-in form                           |
| :heavy_check_mark:  | Displays the log-in form in English or French based on the user’s computer language setting            |
| :heavy_check_mark:  | Translates error control messages into English or French based on the user’s computer language setting |

### 2. Customers
| Status | Task                                                                           |
|--------|--------------------------------------------------------------------------------|
|        | Customer records can be added, updated, and deleted                            |
|        | Collect customer name, address, postal code, and phone number                  |
|        | Country and first-level division data is pre-populated in separate combo boxes |
|        | All of the original customer information is displayed on the update form       |
|        | All of the fields can be updated except for Customer_ID                        |
|        | Customer data is displayed using a TableView                                   |
|        | Message is displayed in the user interface when a customer is deleted          |

### 3. Appointments
| Status | Task                                                                       |
|--------|----------------------------------------------------------------------------|
|        | Appointments can be added, updated, and deleted                            |
|        | Contact name is assigned to an appointment using a drop-down menu          |
|        | Custom message is displayed in the user interface when canceled (deleted)  |
|        | Appointment_ID is auto-generated and disabled throughout the application   |
|        | Appointment information is displayed on the update form in local time zone |
|        | Allow user to view appointment schedules by month and week                 |
|        | Allow user to adjust appointment times                                     |

### 4. Logical Error Checks
| Status              | Logical Errors                                                                                                 |
|---------------------|----------------------------------------------------------------------------------------------------------------|
|                     | Scheduling an appointment outside of business hours defined as 8:00 a.m. to 10:00 p.m. EST, including weekends |
|                     | Scheduling overlapping appointments for customers                                                              |
| :heavy_check_mark:  | Entering an incorrect username and password                                                                    |

### 5. Reports
| Status | Description                                                                                                                                                       |
|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|        | Total number of customer appointments by type and month                                                                                                           |
|        | Schedule for each contact in your organization that includes appointment ID, title, type and description, start date and time, end date and time, and customer ID |
|        | Security report: application entry attempts                                                                                                                       |

### 6. Final Tasks
| Status | Description                                                                                                                        |
|--------|------------------------------------------------------------------------------------------------------------------------------------|
|        | Write at least two different lambda expressions                                                                                    |
|        | Record all user log-in attempts, dates, and time stamps and whether each attempt was successful in a file named login_activity.txt |
|        | Provide Javadoc comments for at least 70 percent of the classes and their members and create an index.html file                    |

### 7. Included in the README.txt file
| Status | Description                                                        |
|--------|--------------------------------------------------------------------|
|        | Title and purpose of application                                   |
|        | Author, contact, app version, date                                 |
|        | IDE including version number                                       |
|        | Full JDK of version used                                           |
|        | JavaFX version compatible with JDK version                         |
|        | Directions for how to run program                                  |
|        | Description of additional report                                   |
|        | MySQL Connector driver version number, including the update number |