Feature: Testing mimic service sprint 6

  @Test_Case_ID_31
  Scenario: To learn a longer path
    Given mimic.jar is running
    When Requesting "http://localhost:8080/test/testar?hello"
    Then Mimic responds with the learning template  
   
  @Test_Case_ID_32
  Scenario: More informative response is provided
    Given mimic.jar is running
    When I try different commands
    Then Mimic responds more informative responses
    
  @Test_Case_ID_33
  Scenario: Getting response by clicking the request
    Given mimic.jar is running
    When I click my request
    Then Mimic is responding with the correct and accordingly response
   

  @Test_Case_ID_34
  Scenario: 
    #Given mimic.jar is running
    #When 
    #Then 
    
  @Test_Case_ID_35
  Scenario: 
    #Given mimic.jar is running
    #When
    #Then 
   

  @Test_Case_ID_36
  Scenario: 
    #Given mimic.jar is running
    #When 
    #Then 

  