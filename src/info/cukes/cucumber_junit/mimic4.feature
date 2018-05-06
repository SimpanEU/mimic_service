Feature: Testing mimic service sprint 4

  @Test_Case_ID_19
  Scenario Outline: 
    Given mimic.jar is running
    When I learn basic math operations
    And I specify a <operator>, <value1>, <value2> and the <sum>
    Then Mimic learns to calculate the anwers itself

    Examples: 
      | operator | value1 | value2 | sum  |
      | "add"    | "1"    | "1"    | "2"  |
      | "add"    | "1"    | "2"    | "3"  |
      | "add"    | "2"    | "2"    | "4"  |
      | "add"    | "2"    | "3"    | "5"  |
      | "add"    | "3"    | "4"    | "7"  |
      | "sub"    | "10"   | "10"   | "0"  |
      | "sub"    | "9"    | "2"    | "7"  |
      | "sub"    | "8"    | "3"    | "5"  |
      | "sub"    | "7"    | "4"    | "3"  |
      | "sub"    | "6"    | "5"    | "1"  |
      | "mult"   | "1"    | "6"    | "6"  |
      | "mult"   | "2"    | "7"    | "14" |
      | "mult"   | "3"    | "8"    | "24" |
      | "mult"   | "4"    | "9"    | "36" |
      | "mult"   | "5"    | "10"   | "50" |
      | "div"    | "10"   | "2"    | "5"  |
      | "div"    | "8"    | "4"    | "2"  |
      | "div"    | "6"    | "3"    | "2"  |
      | "div"    | "5"    | "1"    | "5"  |
      | "div"    | "4"    | "2"    | "2"  |
      
  @Test_Case_ID_20
  Scenario: 
    Given mimic.jar is running
    When I ask for math operations that has not been learnt
    And I loop a series of new add calcuations
    And I loop a series of new sub calculations
    And I loop a series of new mult calculations
    And I loop a series of new div calculations
    Then Mimic is responding with correct sum in each loop
    
  @Test_Case_ID_21
  Scenario:
    Given mimic.jar is running
    When I learn a sequence of responses
    And I use unlearn in the middle of a sequence
    Then All previously learned responses is removed
    
  @Test_Case_ID_22
  Scenario: 
    Given mimic.jar is running
    When I learn a sequence of responses
    And I call for request twice on last state
    And I use unlearn
    Then I unlearn the last response

  @Test_Case_ID_23
  Scenario: 
    Given mimic.jar is running
    When I learn a sequence of responses
    And I use resetState
    And I use unlearn
    Then All responses for that request is unlearned
    
  @Test_Case_ID_24
  Scenario: Testing the unlearn function 
    Given mimic.jar is running
    And mimic has several learned requests and responses
    And I am saving a new response
    When I use "unlearn" function after the new response has been saved
    Then response to the last request changed
    