# SwagLabs Automation Testing Project

This project automates end-to-end UI tests for the SwagLabs application using Java, Cucumber, Selenium WebDriver, and Allure Reports.

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver
- Cucumber (BDD)
- JUnit
- Logback
- Allure for reporting
- GitLab CI for automation

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

## GitLab CI Integration

This project is fully integrated with GitLab CI/CD:
- Tests are run on every push
- Allure and Surefire reports are saved as artifacts
- JUnit results are parsed and shown in GitLab UI

## Reporting

- target/cucumber-report.html
- target/surefire-reports/
- target/allure-report/ (generated via allure generate)

## Author

Maintained by Anastasiia Riazanova

