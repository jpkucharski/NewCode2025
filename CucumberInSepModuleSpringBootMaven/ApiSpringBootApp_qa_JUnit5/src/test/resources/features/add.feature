@Tests_1
Feature: Addition API

  @Scenario_1
  Scenario Outline: Adding two numbers
    Given the application is running
    When I send a POST request to "/api/add" with parameters a=<a> and b=<b>
    Then I should receive a 200 OK response with result <sum>

    Examples:
      | a  | b  | sum |
      |  5 |  3 |  8  |
      |  10 | 2 | 12  |
      | -1 |  4 |  3  |
      |  0 |  0 |  0  |

  @Scenario_2
  Scenario Outline: Adding two numbers from scenario_2
    Given the application is running
    When I send a POST request to "/api/add" with parameters a=<a> and b=<b>
    Then I should receive a 200 OK response with result <sum>

    Examples:
      | a  | b  | sum  |
      |  0 |  0 |  0   |
      |  0 | 2  |  2   |
      | -1 |  0 |  -1  |
      | -2 |  0 |  -2  |