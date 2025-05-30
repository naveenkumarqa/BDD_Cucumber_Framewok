Feature: End-to-End User Login and Logout

  Background: 
    Given User launches the browser
    And navigates to the application URL

  Scenario: User logs in successfully, navigates through the app, and logs out
    When User validates the Home Page
    And clicks on Practice
    
    Then User lands on the Practice Page
    When User clicks on Test Login
    
    Then User lands on the Test Page
    When User enters valid credentials
    And clicks on Submit
    
    Then User lands on the Login Success page
    When User clicks on Logout
    
    Then User is logged out successfully

   @Smoke 
   Scenario: Successful Login via Test URL
    When User navigates to Test URL    
    When User enters valid credentials
    And clicks on Submit
    
    Then User lands on the Login Success page