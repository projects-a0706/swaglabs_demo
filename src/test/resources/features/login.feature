Feature: Login

    @1_001
    Scenario: Valid login with standard_user
        Given the user is on the login page
        When the user enters username "standard_user" and password "secret_sauce"
        And the user clicks on login button
        Then the user should be redirected to the inventory page


    @1_002
    Scenario: Login with locked_out_user
        Given the user is on the login page
        When the user enters username "locked_out_user" and password "secret_sauce"
        And the user clicks on login button expecting an error
        Then the user should stay on the login page
        And the error message should be displayed "Epic sadface: Sorry, this user has been locked out."


    @1_003
    Scenario: Invalid login with no credentials
        Given the user is on the login page
        When the user clicks on login button expecting an error
        Then the user should stay on the login page
        And the error message should be displayed "Epic sadface: Username is required"


    @1_006
    Scenario: Invalid login with invalid credentials
        Given the user is on the login page
        When the user enters username "user123" and password "password123"
        And the user clicks on login button expecting an error
        Then the user should stay on the login page
        And the error message should be displayed "Epic sadface: Username and password do not match any user in this service"
        And x-icons should be displayed in the username and password fields

