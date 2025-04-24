Feature: API Status Check

  @Scenario_1
  Scenario: Check if the API status endpoint is working
    Given the application is running
    When I call the GET api status endpoint
    Then I should receive a 200 OK response with message "Service is Up!!"

  @Scenario_2
  Scenario: Check if the API status endpoint is working but with wrong message
    Given the application is running
    When I call the GET api status endpoint
    Then I should receive a 200 OK but the response with message is different "Service is not Up!!"
