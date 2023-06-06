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

This week our core deliverables focused on implementing the use of a streams and lambdas, while also saving and retrieving objects. A key feature that is introduced this week allows the user to enter in another fuel card number after the initial output is received. Furthermore, a new feature is also introduced that allows the user to exit the program at any time after a fuel card number is provided. The output received this week provides a breakdown total by state for the specified fuel card number.


## <a id="features"></a>Features

| Feature | Description |
|---------|-------------|
| User Input of File Path | User is prompted to enter in a local file path to begin the SettleEase parsing of data |
| Report Creation | Once valid file is received we save the file to a Report |
| Exceptions Handling | If file path is unreadable or does not exist an exception is thrown for user feedback and resource closing |
| Data Parsing | We parse the CSV file contents to return specific categories |
| Column Splitting | We take unstructured data and split data into columns to extract desired categories |
| Report Saving | We save the row data contents to the Report object |
| Report ID Confirmation | We ensure that the appropriate Report ID is saved to the report with processed data |
| User Input of Fuel Card No. | User is prompted to enter in fuel card number and SettlEase will return the fuel card number total if valid |
| Fuel Card Total Breakdown | Fuel card total breakdown by state is returned to the console, also with an overall total for the fuel card.|
| Repeating Fuel Card No. | User is prompted to enter in a new fuel card number and SettlEase will return the fuel card number total if valid |
| Exit Program | User is prompted to enter in 'exit' at any time after the fuel card input has been made to close the program. |



## <a id="installation"></a>Installation

1. Download code base from Github repository.
2. You will need to locate your local file path for where the CSV file is saved at. This path will look like this [your local path]/settle-ease/src/test/resources/TR123325.csv
3. Run program
4. You will be prompted to enter the file path (refer to step 2).
5. Once the correct file path is entered, SettleEase will process the CSV contenst and the results will be rendered to the Console with the Report ID.
6. SettleEase 
7. Once processed, SettleEase will prompt you to enter in a Fuel Card number. A valid Fuel Card number to use for testing is "012". Card number "012" is the only fuel card number in this file with multiple states available for a breakdown. An invalid Fuel Card number to use for testing is "069".


## <a id="usage"></a>Usage
Below are the instructions for running this application assuming you have successfully followed the Installation steps:

1. Run program with local IDE (Eclipse).
2. You will be prompted to enter the file path (refer to step 2 of Installation).
3. Once the correct file path is entered, SettleEase will process the CSV contenst and the results will be rendered to the Console with the Report ID.
4. SettleEase shall prompt you to enter in a Fuel Card number.
5. If valid, SettleEase shall return to you the total spend for specified Fuel Card number.
6. SettleEase will prompt you to enter in a new fuel card number whether input is valid or not, also allowing the user to 'exit' the program.
7. After 'exit' the program successfully terminates.


## <a id="contributing"></a>Contributing

chatGPT played a large role in the development of this code, with the developer playing a "prompt engineer" role. Core requirements required specific programming concepts that chatGPT was not natively familiar with in regard to its direct implementation of this project. Large amounts of refactoring and re-prompting also took place to get desired output.


