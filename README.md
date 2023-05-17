# SettleEase

We are creating a Java microservice for a trucking company called "SettleEase". The microservice will read CSV files from an FTP server, process the data, and store it in a database. The microservice will be designed to dynamically handle various types of reports, ensuring flexibility in processing different datasets. However, the main goal of this use case is to automate the process of assigning data to individual contractors for settlement deductions through a "Fuel Report", providing transparency and eliminating manual effort.


## Table of Contents

- [Current Week Milestones](#current-week-milestones)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)


## <a id="current-week-milestones"></a>Current Week Milestones

As SettleEase is uniquely tailored to be a project that is built up each week, this section highlights the key accomplishments for the week. 

This week our core demonstration includes processing contents of a CSV file by processing each row of data and saving it to a report. Through our developments this week we highlighted the use of key programming concepts such as abstract classes, inheritance, polymorphism, and upcasting.


## <a id="features"></a>Features

| Feature | Description |
|---------|-------------|
| User Input of File Path | User is prompted to enter in a local file path to begin the SettleEase parsing of data |
| Report Creation | Once valid file is received we save the file to a Report |
| Data Parsing | We parse the CSV file contents to return specific categories |
| Column Splitting | We take unstructured data and split data into columns to extract desired categories |
| Report Saving | We save the row data contents to the Report object |
| Report ID Confirmation | We ensure that the appropriate Report ID is saved to the report with processed data |



## <a id="installation"></a>Installation

1. Download code base from Github repository.
2. You will need to locate your local file path for where the CSV file is saved at. This path will look like this [your local path]/settle-ease/src/test/resources/TR123325.csv
3. Run program
4. You will be prompted to enter the file path (refer to step 2).
5. Once the correct file path is entered, SettleEase will process the CSV contenst and the results will be rendered to the Console with the Report ID.


## <a id="usage"></a>Usage
Below are the instructions for running this application assuming you have successfully followed the Installation steps:

1. Run program with local IDE (Eclipse).
2. You will be prompted to enter the file path (refer to step 2 of Installation).
3. Once the correct file path is entered, SettleEase will process the CSV contenst and the results will be rendered to the Console with the Report ID.

Other Important Notes:
This week we started to implement Junit tests but ran into some Eclipse issues and ultimately these were not used as intended. As we look forward in future weeks we will incorporate better usage of these tests where applicable.


## <a id="contributing"></a>Contributing

chatGPT played a large role in the development of this code, with the developer playing a "prompt engineer" role. Core requirements required specific programming concepts that chatGPT was not natively familiar with in regard to its direct implementation of this project. Large amounts of refactoring and re-prompting also took place to get desired output.


