Feature: Learn requests, get responses and unlearn requests in the mimic service

  @Test_Case_ID_7
  Scenario: Learning and requesting responses
    Given mimic.jar is running
    When I learn a response
    And I learn a request
    Then mimic.jar is responding with correct response

	@Test_Case_ID_8
  Scenario: Unlearning a previous request/response
    Given mimic.jar is running
    When I unlearn the previous request/response using the "unlearn" command
    Then Previous request/response is removed
    
  @Test_Case_ID_9
	Scenario: Shutting down current running service
    Given mimic.jar is running
    When I use the mimic.jar shutdown function
    Then mimic.jar is not running
    
  @Test_Case_ID_10
  Scenario: Correct an incorrect response
    Given mimic.jar is running
    When I learn a response
    And I learn a request
    And I learn a new response
    And I learn a request
    Then mimic.jar is responding with correct new response
    
    
  @Test_Case_ID_11
 	Scenario Outline: Learning and requesting responses
 	  Given mimic.jar is running
  	When I learn a <response> and a <request>
  	Then mimic.jar is responding with correct <response>

  Examples:
    |  request  |  response |
    |  "banana" |  "yellow" |
    |  "volvo"  |  "car"    |
    |  "green"  |  "green"  |
    |  1010     |  0101     |
    |  0000     |  0000     |