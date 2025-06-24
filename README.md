# SwagLabs Automation Testing Project

This project automates end-to-end UI tests for the SwagLabs application using Java, Cucumber, Selenium WebDriver, and Allure Reports.

## Documenation (URLs)

- Test Plan [Google Docs] (https://docs.google.com/document/d/1HGZuspp0vHq-0HXlNjzDKh6FrcX_1t6QyWqEDxzhwJk/edit?usp=sharing)
- Test Cases [Google Sheets] (https://docs.google.com/spreadsheets/d/1z3Wg7XP8b0x8eHKzt9pdLMvBqF9KBabHoTUV6s4eywM/edit?usp=sharing)

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- JUnit
- Logback
- Allure, Surefire
- Jenkins, GitHub Actions

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

## GitHub Actions

This project uses GitHub Actions for continuous integration. Every push to the repository triggers an automated workflow that:
- Checks out the latest code
- Installs Java and Maven
- Executes all automated tests
- Uploads test results as artifacts (Surefire and Allure)

## Reporting

- target/cucumber-report.html
- target/surefire-reports
- target/allure-report

## Author

Maintained by Anastasiia Riazanova

