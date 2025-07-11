# SwagLabs Automation Testing Project

This is a demo automation project created to showcase my skills in end-to-end UI testing.
It automates user workflows for the Swag Labs e-commerce application using Java, Cucumber, Selenium WebDriver, and Allure Reports.
The project is intended for learning, portfolio, and demonstration purposes only.

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- JUnit
- Logback 
- Allure, Surefire
- Jenkins

## Project Structure

- main/java/pages - Page Object classes 
- test/java/stepdefinitions - Step definitions for Cucumber 
- test/java/utils - Hooks and common utilities 
- test/java/runners - Test runners

## How to Run Tests Locally

### Prerequisites
- Java 17+
- Maven 3.8+
- Chrome Browser

### Commands

1. Run tests:
   mvn clean test

2. Generate Allure report:
   allure serve target/allure-results

## Jenkins CI Integration

This project is integrated with Jenkins to automate test execution and reporting. It is hosted locally and can be shown on request.

## Reporting

- target/cucumber-report.html
- target/surefire-reports
- target/allure-report

## Author

Maintained by Anastasiia Riazanova

