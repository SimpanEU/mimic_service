Feature: Testing mimic service sprint 4

  @Test_Case_ID_19
  Scenario Outline: 
    Given mimic.jar is running
    When I learn basic math operations
    Then Mimic learns to calculate the anwers itself

    Examples: 
      | "operator" | "value1" | "value2" | "sum" |

  @Test_Case_ID_20
  Scenario Outline: 
    Given mimic.jar is running
    When I ask for math operations that has not been learnt
    Then Mimic is responding with correct answer

    Examples: 
      | "operator" | "value1" | "value2" | "sum" |

  @Test_Case_ID_21
  Scenario: 
    Given mimic.jar is running

  @Test_Case_ID_22
  Scenario: 
    Given mimic.jar is running

  @Test_Case_ID_23
  Scenario: 
    Given mimic.jar is running

  @Test_Case_ID_24
  Scenario: 
    Given mimic.jar is running

  @Test_Case_ID_25
  Scenario: 
    Given mimic.jar is running
