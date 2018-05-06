package info.cukes.cucumber_junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MimicStepDef456 {

	private final static String host = "http://localhost:8080/";
	private HttpServiceCaller service = new HttpServiceCaller();

	
	@When("^I learn basic math operations$")
	public void i_learn_basic_math_operations() throws Throwable {
	}

	@When("^I specify a \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and the \"([^\"]*)\"$")
	public void i_specify_a_and_the(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		System.out.println("Sending: http://localhost:8080/LearnNextResponse?text="+arg4);
		service.executeGetRequest("http://localhost:8080/LearnNextResponse?text="+arg4);
		System.out.println("Sending: http://localhost:8080/"+arg1+"?value1="+arg2+"&value2="+arg3);
		System.out.println(service.executeGetRequest("http://localhost:8080/"+arg1+"?value1="+arg2+"&value2="+arg3));
	}

	@Then("^Mimic learns to calculate the anwers itself$")
	public void mimic_learns_to_calculate_the_anwers_itself() throws Throwable {
	}
	
	
	
}
