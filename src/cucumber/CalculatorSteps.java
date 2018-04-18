package cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertEquals;

public class CalculatorSteps {

	private final static String host = "http://localhost:1235/";
	private HttpServiceCaller service=new HttpServiceCaller();
	private String response;
	

	@Given("^Calculator\\.jar is running and ready for input$")
	public void calculator_is_ready_for_input() throws Throwable {
		String request=host+"operators";
		String operators=service.executeGetRequest(request);
		assertEquals(operators, "add, sub, mult, div");
	}

	@When("^I add (\\d+) to (\\d+)$")
	public void i_add_to(int arg1, int arg2) throws Throwable {		
		String request=host+"add?value1="+Integer.toString(arg1)+"&value2="+Integer.toString(arg2);
		response=service.executeGetRequest(request);
	}
	
	@When("^I subtract (\\d+) from (\\d+)$")
	public void i_subtract_from(int arg1, int arg2) throws Throwable {
		String request=host+"sub?value1="+Integer.toString(arg1)+"&value2="+Integer.toString(arg2);
		response=service.executeGetRequest(request);
	}
	
	@When("^I multiply (\\d+) with (\\d+)$")
	public void i_multiply_with(int arg1, int arg2) throws Throwable {
		String request=host+"mult?value1="+Integer.toString(arg1)+"&value2="+Integer.toString(arg2);
		response=service.executeGetRequest(request);
	}
	
	@When("^I divide (\\d+) with (\\d+)$")
	public void i_divide_with(int arg1, int arg2) throws Throwable {
		String request=host+"div?value1="+Integer.toString(arg1)+"&value2="+Integer.toString(arg2);
		response=service.executeGetRequest(request);
	}
	
	@Then("^Calculator returns the total summary which is (\\d+)$")
	public void calculator_returns_the_total_summary_which_is(int arg1) throws Throwable {
		assertEquals(arg1, Integer.parseInt(response));
	}
}
