Feature: Learn requests, get responses and unlearn requests in the mimic service

  @Test_Case_ID_6
  Scenario: Learning and requesting responses
    Given mimic.jar is running
    When I learn a response
    And I learn a request
    Then mimic.jar is responding with correct response

	@Test_Case_ID_7
  Scenario: Unlearning a previous request/response
    Given mimic.jar is running
    When I unlearn the previous request/response using the "unlearn" command
    Then Previous request/response is removed
    
  @Test_Case_ID_8
	Scenario: Shutting down current running service
    Given mimic.jar is running
    When I use the mimic.jar shutdown function
    Then mimic.jar is not running
    
  @Test_Case_ID_9
  Scenario: Correct an incorrect response
    Given mimic.jar is running
    When I learn a response
    And I learn a request
    And I learn a new response
    And I learn a request
    Then mimic.jar is responding with correct new response
    
    
    
    
  @Test_Case_ID_10
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
    
    
  @Test_Case_ID_11 
  Scenario: Learning and requesting videos
    Given mimic.jar is running
    When I learn a video response
    And I learn a video request
    Then mimic.jar is responding with correct video response
  
  
  ## unlearnAll FUNCTION- UserStory6 ##
  
   @Test_Case_ID_12
   Scenario: unlearn all the responses
   Given mimic.jar is running
   When  I unlearn the previous responses using the "unlearnAll" command
   Then  All previous responses are moved
   
   @Test_Case_ID_13
   Scenario Outline: Re-run all the request
   Given mimic.jar is running
   When I run the previous <request> again
   Then Previous <response> are removed

  Examples:
    |  request  |  response |
    |  "banana" |  "yellow" |
    |  "volvo"  |  "car"    |
    |  "green"  |  "green"  |
    |  1010     |  0101     |
    |  0000     |  0000     |