Feature: Addition API test
  Scenario Outline: Add two numbers
    Given the service is up
    When I send request to add fallowing numbers
    | a | b | sum |
    |<a>|<b>|<sum>|
    Then the response status code is 200
    And the response message is correct

    Examples:
      | a  | b  | sum  |
      |  3 |  5 |  8   |
      | -2 |  7 |  5   |
      |  0 |  7 |  7   |