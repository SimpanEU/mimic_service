Feature: Testing mimic service sprint 3

  # User Story 6: Tests unlearnAll
  @Test_Case_ID_12
  Scenario: unlearn all the responses
    Given mimic.jar is running
    When I unlearn the previous responses using the "unlearnAll" command
    Then All previous responses are removed

  # User Story 6: Tests unlearnAll
  @Test_Case_ID_13
  Scenario Outline: Re-run all the request
    Given mimic.jar is running
    When I run the previous <request> again
    Then Previous <response> are removed

    Examples: 
      | request  | response |
      | "banana" | "yellow" |
      | "volvo"  | "car"    |
      | "green"  | "green"  |
      |     1010 |      101 |
      |     0000 |     0000 |

  # User Story 6: Tests relearn function
  @Test_Case_ID_14
  Scenario: Correct an incorrect response
    Given mimic.jar is running
    When I learn a response
    And I learn a request
    And I learn a new response
    And I learn a request
    Then mimic.jar is responding with correct new response

  # User Story 7: Test states responses
  @Test_Case_ID_15
  Scenario Outline: Learning and requesting responses
    Given mimic.jar is running
    When I learn a <response> and a <request>
    Then mimic.jar is responding with correct <response>

    Examples: 
      | request | response |
      | "test"  | "state1" |
      | "test"  | "state2" |
      | "test"  | "state3" |

  # User Story 8: Test resetState responses
  @Test_Case_ID_16
  Scenario Outline: Testing the states
    Given mimic.jar is running
    And <request> has several learned states
    When I use resetState function
    Then <request> returns correct <response>

    Examples: 
      | request | response |
      | "test"  | "state1" |
      | "test"  | "state2" |
      | "test"  | "state3" |

  # Tests functionality of saving data
  @Test_Case_ID_17
  Scenario Outline: Restart mimic and see if entries has been saved
    Given I learn a <response> and a <request>
    And mimic.jar is not running
    When mimic.jar is launched
    Then mimic.jar is responding with correct <response>

    Examples: 
      | request  | response |
      | "banana" | "yellow" |
      | "volvo"  | "car"    |

  @Test_Case_ID_18
  Scenario: Unlearn with states
    Given mimic.jar is running
    And unlearnAll is called
    When Request "test" has "15" learned responses
    And I use unlearn "x" times
    Then Request "test" returns correct state response
