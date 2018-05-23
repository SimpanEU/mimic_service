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
    #Gives the command responses in the console as system printline