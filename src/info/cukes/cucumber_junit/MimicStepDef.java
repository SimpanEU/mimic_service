package info.cukes.cucumber_junit;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MimicStepDef {
	
	private final static String host = "http://localhost:8080/";
	private HttpServiceCaller service=new HttpServiceCaller();
	private String response;
	

	@Given("^mimic\\.jar is running$")
	public void mimic_jar_is_running() throws Throwable {
		String request=host+"unlearn";
		String response=service.executeGetRequest(request);
		assertEquals(response, "OK");
	}

	@When("^I learn a reponse$")
	public void i_learn_a_reponse() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^I request a reponse$")
	public void i_request_a_reponse() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^mimic\\.jar is responding with correct response$")
	public void mimic_jar_is_responding_with_correct_response() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^I unlearn the previous request/response using the \"([^\"]*)\" command$")
	public void i_unlearn_the_previous_request_response_using_the_command(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^Previous request/response is removed$")
	public void previous_request_response_is_removed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^I use the mimic\\.jar shutdown function$")
	public void i_use_the_mimic_jar_shutdown_function() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^mimic\\.jar is not running$")
	public void mimic_jar_is_not_running() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

}
