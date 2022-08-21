Feature: As a registered user of carb manager web app, I want to login to carb manager web app.

  Scenario: Verify user is able to navigate to sign in page.
    Given User visits the landing page of carb manager web app.
    When User clicks on sign in link
    Then User should be redirected to sign in page
    And Verify sign in page contents




