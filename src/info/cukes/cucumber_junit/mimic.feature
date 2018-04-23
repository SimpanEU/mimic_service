Feature: Learn requests, get responses and unlearn requests in the mimic service


  Scenario: Learning and requesting responses
    Given mimic.jar is running
    When I learn a response
    And I learn a request
    Then mimic.jar is responding with correct response

  Scenario: Unlearning a previous request/response
    Given mimic.jar is running
    When I unlearn the previous request/response using the "unlearn" command
    Then Previous request/response is removed
    
	Scenario: Shutting down current running service
    Given mimic.jar is running
    When I use the mimic.jar shutdown function
    Then mimic.jar is not running
    
    
    
    
    
# Scenario Outline: Learning and requesting responses
#  Given mimic.jar is running
#  When I learn a <request>
#	 And I learn a <response>
#  Then mimic.jar is responding with correct <response>
#
#  Examples:
#    | request | response |
#    |  apple  |  fruit   |
#    |  volvo  |  car     |
#    |  1010   |  0101    |