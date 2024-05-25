@Signin
Feature: Register for a New Account from the Homepage

  @NewAccountRegister
  Scenario: Navigate to Account Registration Page and Complete Registration
    Given I am a new user on the IMDb homepage
    When I click the “Sign In” link and then click the “Create a New Account” link
    Then I should be directed to the account registration page
    When I on the account registration page
    And I fill in the registration form with all required details
      |  name  | email                |password             |password_confirmation|
      |   Test | test                  |  12345678*         |  12345678*  |
    Then I should be able to complete the registration process and land on the authorized member page

  @PreventRegistration
  Scenario: Prevent Registration with Existing Email
    Given I am on the account registration page
    When I attempt to register with an email address that is already in use
      | email                |password   |password_confirmation|
      |jagadax2508@gmail.com |password1234|password1234         |
    Then I should see an error message "You indicated you're a new customer, but an account already exists with the email address"

  @Login
  Scenario: Logout and Login Functionality
    Given I am logged into the member page
    When I log out from the application
    Then I should be redirected to the login page and not be able to access the member page
    When I am on the login page
    And I fill in my email and password correctly
    Then I should be able to login and access the member page again