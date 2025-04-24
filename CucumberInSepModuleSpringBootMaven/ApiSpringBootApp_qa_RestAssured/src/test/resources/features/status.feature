Feature: Status Api Test

  Scenario: Get status response
    Given the service is up
    When I request service status
    Then the response status code is 200
    And the response message is "Service is Up!!"