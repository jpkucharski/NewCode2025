Feature: Status Api Test

  Scenario: Check if the API status endpoint is working
    Given the application is running
    When I call the GET api status endpoint
    Then I should receive a 200 OK response with message "Service is Up!!"