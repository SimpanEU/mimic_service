Feature: Testing mimic service sprint 5

  @Test_Case_ID_25
  Scenario: Trying to learn without specifying a value
    Given mimic.jar is running
    When Requesting "localhost:8080/test?param"
    Then Mimic responds with the learning template

  @Test_Case_ID_26
  Scenario: View all learned responses
    Given mimic.jar is running
    And I have several learned requests
    When I ask for list of learned responses
    Then Mimic returns list of learned responses

  @Test_Case_ID_27
  Scenario: Use examples that begins or ends with the same characters
    Given mimic.jar is running

  #   When x
  #   Then x
  @Test_Case_ID_28
  Scenario Outline: Learn mock complex response faster (xml/json)
    Given mimic.jar is running
    When I learn complex operations <value1> <value2> <addsum> <subsum> <multsum>
    Then Mimic has learned the complex answers

    Examples: 
      | value1 | value2 | addsum | subsum | multsum |
      | "1"    | "1"    | "2"    | "0"    | "1"     |
      | "3"    | "2"    | "5"    | "1"    | "6"     |
      | "4"    | "2"    | "6"    | "2"    | "8"     |
      
  #    | "4"    | "3"    | "7"    | "1"    | "12"    |
  #    | "5"    | "1"    | "6"    | "4"    | "5"     |
  #    | "9"    | "7"    | "16"   | "2"    | "63"    |
  #    | "6"    | "3"    | "9"    | "3"    | "18"    |

  @Test_Case_ID_29
  Scenario: Test complex answers
    Given mimic.jar is running
    When I ask for complex operations
    Then Mimic is responding with correct answers
