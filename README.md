# SettleEase

SettleEase is a microservice designed to automate payroll fuel deductions for contractors in a trucking company. It reads CSV files from an FTP server, processes the data, and stores it in a database. The microservice is designed to handle different types of reports dynamically, ensuring flexibility in processing various datasets. The main goal of SettleEase however is to automate the assignment of fuel spend to individual contractors for settlement deductions. This automation provides transparency and eliminates manual effort, streamlining the process for efficient payroll management.


## Table of Contents

- [Current Week Milestones](#current-week-milestones)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)


## <a id="current-week-milestones"></a>Current Week Milestones

As SettleEase is uniquely tailored to be a project that is built up each week, this section highlights the key accomplishments for the week: 

This week our core deliverables focused on turning SettleEase into a practical application through a database implementation. Several key features were introduced that start with allowing the user to enter in a file path for a database immediately after inputting a valid csv file path. This will establish a connection to the database, allowing SettleEase to process the csv provided data and create an Employee table that is then used to provide value to the user by allowing sorting, ranking, and returning top 3 fuel providers. Upon exiting, we are providing a valuable feature in deleting all the tables and data to provide for a fresh restart experience upon the start of the program.


## <a id="features"></a>Features

| Feature | Description |
|---------|-------------|
| User Input of CSV File Path | User is prompted to enter in a local file path to begin the SettleEase parsing of data |
| User Input of Database File Path | After valid CSV file path, User is prompted to enter in a local database file path to connect to the database. |
| Report Creation | Once valid file is received we save the file to a Report |
| Exceptions Handling | If file path is unreadable or does not exist an exception is thrown for user feedback and resource closing |
| Data Parsing | We parse the CSV file concurrently contents to return specific categories |
| Column Splitting | We take unstructured data and split data into columns to extract desired categories |
| Report Saving | We save the row data contents to the Report object |
| Report ID Confirmation | We ensure that the appropriate Report ID is saved to the report with processed data |
| Table Creation | While SettleEase is processing, we are also creating two database tables, one being the FuelCard for employee information and the other being the dynamic csv content for FuelData |
| Table Inserting | Valid csv file contents are to be inserted into the database with the Tables getting updated for later use of retrieval operations. |
| User Choices | SettleEase prompts the user to choose between entering a fuel card number, retrieving a descending order of transactions, returning the top 3 fuel spenders by employee name, or exiting the program. |
| User Input of Fuel Card No. | If user enters in fuel card number SettlEase will return the fuel card number total and state breakdown, along with individual card ranking. |
| Rank | If user enters 'rank' SettleEase shall sort transactions in descending order returning to the console.|
| Top 3 | If user enters 'top 3' SettlEase shall provide the top three fuel spenders by total and employee name.|
| Exit Program | User is prompted to enter in 'exit' at any time. By doing so, SettleEase shall delete the existing tables and its content, allowing for clean user experience in restarting the program. |



## <a id="installation"></a>Installation

1. Download code base from Github repository.
2. You will need to locate your local file path for where the CSV file is saved at. This path will look like this [your local path]/settle-ease/src/test/resources/TR123325.csv
3.You will need to locate your local file path for where the database file is save at. This path will look like this  [your local path]/settle-ease/settle-ease/src/test/resources/FuelData.db. Please note that adding jdbc:sqlite: in front of the db file path will result in an incorrect path, as this is added in for the user automatically within the code.
4. Run program
5. You will be prompted to enter the csv file path (refer to step 2).
6. You will be prompted to enter the database file path (refer to step 3).
7. Once the correct file path is entered, SettleEase will process the CSV contenst and connect to the appropriate database and the results will be rendered to the Console with the Report ID.
8. SettleEase will prompt the user with several options between entering a fuel card number, sorting transactions with 'rank', returning the top 3 fuel spenders, or exiting the program.
9. A valid Fuel Card number to use for testing is "012". Card number "012" is the only fuel card number in this file with multiple states available for a breakdown. An invalid Fuel Card number to use for testing is "069". Card number "012" can also be used to test the aggregating feature that is returned with the top 3 as this card has multiple transactions in the csv file that are added up.


## <a id="usage"></a>Usage
Below are the instructions for running this application assuming you have successfully followed the Installation steps:

1. Run program with local IDE (Eclipse).
2. You will be prompted to enter the csv file path (refer to step 2 of Installation).
3. You will be prompted to enter the database file path (refer to step 3 of Installation).
4. Once the correct file path is entered, SettleEase will process the CSV contenst, connect to the database and results will be rendered to the Console with the Report ID.
5. SettleEase shall prompt the User several options to choose from (enter fuel card number, rank to sort transactions, top 3 to return to three fuel spenders, and ability to exit).
6. With each selected option SettleEase will return the desired output, with connection to the database.
8. After 'exit' the program successfully terminates and will delete the tables and contents from the database to allow for a fresh restart of the program upon next use.


## <a id="contributing"></a>Contributing

chatGPT played a large role in the development of this code, with the developer playing a "prompt engineer" role. Core requirements required specific programming concepts that chatGPT was not natively familiar with in regard to its direct implementation of this project. Large amounts of refactoring and re-prompting also took place to get desired output.


