Feature: Testing mimic service sprint 5

  @Test_Case_ID_25
  Scenario: Trying to learn without specifying a value
    Given mimic.jar is running
    When Requesting "localhost:8080/test?param"
    Then Mimic responds with the learning template

  @Test_Case_ID_26
  Scenario: View all learned responses
    Given mimic.jar is running
    When I ask for list of learned responses
    Then Mimic returns list of learned responses

  @Test_Case_ID_27
  Scenario: Title of your scenario
    Given mimic.jar is running
    When I complete action
    Then I validate the outcomes

  @Test_Case_ID_28
  Scenario Outline: Title of your scenario outline
    Given mimic.jar is running
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
