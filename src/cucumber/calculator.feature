Feature: Calculate numbers using addition, subtraction, divied by and multiplyed by.

  Scenario: Calculate the summary of two numbers using addition
    Given Calculator.jar is running and ready for input
    When I add 15 to 25
    Then Calculator returns the total summary which is 40
    
  Scenario: Calculate the summary of two numbers using subtraction
    Given Calculator.jar is running and ready for input
    When I subtract 10 from 20
    Then Calculator returns the total summary which is 10
    
  Scenario: Calculate the product of two factors using multiply
    Given Calculator.jar is running and ready for input
    When I multiply 5 with 9
    Then Calculator returns the total summary which is 45
    
  Scenario: Calculate the result of dividing two numbers
  	Given Calculator.jar is running and ready for input
  	When I divide 100 with 20
  	Then Calculator returns the total summary which is 5