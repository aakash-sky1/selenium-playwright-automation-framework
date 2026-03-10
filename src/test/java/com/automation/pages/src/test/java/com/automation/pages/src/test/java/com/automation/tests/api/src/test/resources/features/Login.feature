Feature: Login Functionality
  As a registered user
  I want to log into the application
  So that I can access my dashboard

  Background:
    Given the user navigates to the login page

  @smoke @regression
  Scenario: Successful login with valid credentials
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be on the dashboard page
    And the welcome message should be visible

  @regression
  Scenario: Login fails with invalid password
    When the user enters username "standard_user" and password "wrong_pass"
    And the user clicks the login button
    Then an error message "Username and password do not match" should appear

  @regression
  Scenario: Login fails with empty credentials
    When the user leaves username and password empty
    And the user clicks the login button
    Then an error message "Username is required" should appear

  @regression
  Scenario Outline: Login with multiple invalid users
    When the user enters username "<username>" and password "<password>"
    And the user clicks the login button
    Then an error message "<error>" should appear

    Examples:
      | username        | password       | error                                     |
      | locked_out_user | secret_sauce   | Sorry, this user has been locked out      |
      | invalid_user    | secret_sauce   | Username and password do not match        |
      | standard_user   |                | Password is required                      |
