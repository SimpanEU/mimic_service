package info.cukes.cucumber_junit;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MimicStepDef456 {

	private final static String host = "http://localhost:8080/";
	private HttpServiceCaller service = new HttpServiceCaller();
	private String response;

	// @Test_Case_ID_19
	@When("^I learn basic math operations$")
	public void i_learn_basic_math_operations() throws Throwable {
	}
	@When("^I specify a \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and the \"([^\"]*)\"$")
	public void i_specify_a_and_the(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		System.out.println("Sending: http://localhost:8080/LearnNextResponse?text="+arg4);
		service.executeGetRequest("http://localhost:8080/LearnNextResponse?text="+arg4);
		System.out.println("Sending: http://localhost:8080/"+arg1+"?value1="+arg2+"&value2="+arg3);
		service.executeGetRequest("http://localhost:8080/"+arg1+"?value1="+arg2+"&value2="+arg3);
	}
	@Then("^Mimic learns to calculate the anwers itself$")
	public void mimic_learns_to_calculate_the_anwers_itself() throws Throwable {
	}
	
	// @Test_Case_ID_20
	@When("^I ask for math operations that has not been learnt$")
	public void i_ask_for_math_operations_that_has_not_been_learnt() throws Throwable {
	}
	@When("^I loop a series of new add calcuations$")
	public void i_loop_a_series_of_new_add_calcuations() throws Throwable {
		for(int i = 0;i<1000;i=i+51) {
			int x = i/2;
			service.executeGetRequest("http://localhost:8080/add?value1="+i+"&value2="+x);
			System.out.println(i+ " + " +x+ " = "+(i+x));
			assertEquals(Integer.toString(i+x), service.executeGetRequest("http://localhost:8080/add?value1="+i+"&value2="+x));
		}
	}
	@When("^I loop a series of new sub calculations$")
	public void i_loop_a_series_of_new_sub_calculations() throws Throwable {
		for(int i = 1000;i>1;i=i-49) {
			int x = i/2;
			service.executeGetRequest("http://localhost:8080/sub?value1="+i+"&value2="+x);
			System.out.println(i+ " - " +x+ " = "+(i-x));
			assertEquals(Integer.toString(i-x), service.executeGetRequest("http://localhost:8080/sub?value1="+i+"&value2="+x));
		}
	}
	@When("^I loop a series of new mult calculations$")
	public void i_loop_a_series_of_new_mult_calculations() throws Throwable {
		for(int i = 0;i<1000;i=i+57) {
			int x = i/2;
			service.executeGetRequest("http://localhost:8080/mult?value1="+i+"&value2="+x);
			System.out.println(i+ " * " +x+ " = "+(i*x));
			assertEquals(Integer.toString(i*x), service.executeGetRequest("http://localhost:8080/mult?value1="+i+"&value2="+x));
		}
	}
	@When("^I loop a series of new div calculations$")
	public void i_loop_a_series_of_new_div_calculations() throws Throwable {
		for(int i = 2;i<1000;i=i+50) {
			int x = (int) (Math.random() * (50 - 2));
			service.executeGetRequest("http://localhost:8080/div?value1="+i+"&value2="+x);
			System.out.println(i+ " / " +x+ " = "+(i/x));
			assertEquals(Integer.toString(i/x), service.executeGetRequest("http://localhost:8080/div?value1="+i+"&value2="+x));
		}
	}
	@Then("^Mimic is responding with correct sum in each loop$")
	public void mimic_is_responding_with_correct_sum_in_each_loop() throws Throwable {
	}
    
	//@Test_Case_ID_21
	@When("^I learn a sequence of responses$")
	public void i_learn_a_sequence_of_responses() throws Throwable {
		System.out.println();
		System.out.println("****************************************************");
		for (int i = 1; i < 6; i++) {
			service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state" + i);
			service.executeGetRequest("http://localhost:8080/testing");
		}
		System.out.println("Request 'testing' has been given 5 responses...");
	}
	@When("^I use unlearn in the middle of a sequence$")
	public void i_use_unlearn_in_the_middle_of_a_sequence() throws Throwable {
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Using resetState... " +service.executeGetRequest("http://localhost:8080/resetstate"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Using unlearn... " +service.executeGetRequest("http://localhost:8080/unlearnresponse"));
	}
	@Then("^All previously learned responses is removed$")
	public void all_previously_learned_responses_is_removed() throws Throwable {
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("assertEquals should match with state2... state3, state4 and state5 should have been unlearned...");
		assertEquals(service.executeGetRequest("http://localhost:8080/testing"), "state2");
	}		

    // @Test_Case_ID_22
	@When("^I call for request twice on last state$")
	public void i_call_for_request_twice_on_last_state() throws Throwable {
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
	}
	@When("^I use unlearn$")
	public void i_use_unlearn() throws Throwable {
		System.out.println("Using unlearn... " +service.executeGetRequest("http://localhost:8080/unlearnresponse"));
	}
	@Then("^I unlearn the last response$")
	public void i_unlearn_the_last_response() throws Throwable {
		System.out.println("Calling for request 'testing' responds: " +service.executeGetRequest("http://localhost:8080/testing"));
		assertEquals(service.executeGetRequest("http://localhost:8080/testing"), "state4");
	}

    //@Test_Case_ID_23
	@When("^I use resetState$")
	public void i_use_resetState() throws Throwable {
		System.out.println("Using resetState... " +service.executeGetRequest("http://localhost:8080/resetstate"));
	}
	@Then("^All responses for that request is unlearned$")
	public void all_responses_for_that_request_is_unlearned() throws Throwable {;
	System.out.println("All responses should be unlearned... 'testing' now responds: " +service.executeGetRequest("http://localhost:8080/testing"));
	}

	// @Test_Case_ID_24
	@Given("^mimic has several learned requests and responses$")
	public void mimic_has_several_learned_requests_and_responses() throws Throwable {
		String response1 = service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state1");
		String request1 = service.executeGetRequest("http://localhost:8080/test1");
		String response2 = service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state2");
		String request2 = service.executeGetRequest("http://localhost:8080/test2");
		String response3 = service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state3");
		String request3 = service.executeGetRequest("http://localhost:8080/test3");
	}
	@Given("^I am saving a new response$")
	public void i_am_saving_a_new_response() throws Throwable {
		String response = service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state4"); 
	}
	@When("^I use \"([^\"]*)\" function after the new response has been saved$")
	public void i_use_function_after_the_new_response_has_been_saved(String arg1) throws Throwable {
		service.executeGetRequest("http://localhost:8080/unlearnresponse");
	}
	@Then("^response to the last request changed$")
	public void response_to_the_last_request_changed() throws Throwable {
		String request = service.executeGetRequest("http://localhost:8080/test3");
		assertEquals(request, "state4");
	}
	
	
	
	
	// @Test_Case_ID_25
	@When("^Requesting \"([^\"]*)\"$")
	public void requesting(String arg1) throws Throwable {
		this.response = service.executeGetRequest("http://localhost:8080/test?param");
		}
	@Then("^Mimic responds with the learning template$")
	public void mimic_responds_with_the_learning_template() throws Throwable {
		String test = "isLearning";
		if(response.contains("Paste or type json")){
			this.response = "isLearning";
		}
		assertEquals(response, test);
	}
	
	// @Test_Case_ID_26
	@Given("^I have several learned requests$")
	public void i_have_several_learned_requests() throws Throwable {
		for (int i = 1; i < 6; i++) {
			service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state" + i);
			service.executeGetRequest("http://localhost:8080/qwe");
			service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state" + i);
			service.executeGetRequest("http://localhost:8080/rty");
			service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=state" + i);
			service.executeGetRequest("http://localhost:8080/lastrequest");
		}
	}
	@When("^I ask for list of learned responses$")
	public void i_ask_for_list_of_learned_responses() throws Throwable {
		this.response = (service.executeGetRequest("http://localhost:8080/ViewRequests"));
	}
	@Then("^Mimic returns list of learned responses$")
	public void mimic_returns_list_of_learned_responses() throws Throwable {
		String s = this.response;
		if (this.response.endsWith("/lastrequest</a></html>")) {
			s = "OK";
		}
		assertEquals(s, "OK");
	}
	
	// @Test_Case_ID_27
	
	// @Test_Case_ID_28
	@When("^I learn complex operations \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_learn_complex_operations(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
		/*service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=	 \r\n" + 
				"	Testing complex answers\r\n" + 
				"\r\n" + 
				"	Number1 = "+arg1+"\r\n" + 
				"	Number2 = "+arg2+"\r\n" + 
				"\r\n" + 
				"	Results\r\n" + 
				"\r\n" + 
				"	Add = "+arg3+"\r\n" + 
				"	Sub = "+arg4+"\r\n" + 
				"	Mult = "+arg5);*/ 
		service.executeGetRequest("http://localhost:8080/LearnNextResponse?text=Testing complex answers. Number1 = "+arg1+" Number2 = "+arg2+" Results: Add = "+arg3+" Sub = "+arg4+" Mult = "+arg5+"&mime=application/xml");
		service.executeGetRequest("http://localhost:8080/ai?number1="+arg1+"&number2="+arg2);
	}
	@Then("^Mimic has learned the complex answers$")
	public void mimic_has_learned_the_complex_answers() throws Throwable {
	}
	@When("^I ask for complex operations$")
	public void i_ask_for_complex_operations() throws Throwable {
	}
	@Then("^Mimic is responding with correct answers$")
	public void mimic_is_responding_with_correct_answers() throws Throwable {
		System.out.println(service.executeGetRequest("http://localhost:8080/ai?number1=20&number2=15"));
	}
	
	
	
	
	
	
	//mimic6.feature
	@When("^I try different commands$")
	public void i_try_different_commands() throws Throwable {
	}

	@Then("^Mimic responds more informative responses$")
	public void mimic_responds_more_informative_responses() throws Throwable {
		System.out.println();
		System.out.println("***");
		System.out.println("resetState returns: " + service.executeGetRequest("http://localhost:8080/resetState"));
		System.out.println("UnlearnResponse returns: " + service.executeGetRequest("http://localhost:8080/unlearnresponse"));
		System.out.println("UnlearnAllResponses returns: " + service.executeGetRequest("http://localhost:8080/unlearnallresponses"));
		System.out.println("AddResponse returns: " + service.executeGetRequest("http://localhost:8080/addresponse"));
		System.out.println("RelearnResponse returns: " + service.executeGetRequest("http://localhost:8080/relearnresponse"));
		System.out.println("KillMimic returns: " + service.executeGetRequest("http://localhost:8080/KillMimic"));
		System.out.println("***");
		System.out.println();
		String user = System.getProperty("user.name");
		String cmd = "java -jar C://Users//" + user + "//eclipse-workspace//cucumber-junit//mimic.jar";
		Runtime.getRuntime().exec(cmd);

	}
	
}
