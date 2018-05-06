package info.cukes.cucumber_junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MimicStepDef456 {

	private final static String host = "http://localhost:8080/";
	private HttpServiceCaller service = new HttpServiceCaller();

	
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
		for(int i = 0;i<1000;i=i+5) {
			int x = i/2;
			service.executeGetRequest("http://localhost:8080/add?value1="+i+"&value2="+x);
			System.out.println(i+ " + " +x+ " = "+(i+x));
			assertEquals(Integer.toString(i+x), service.executeGetRequest("http://localhost:8080/add?value1="+i+"&value2="+x));
		}
	}
	@When("^I loop a series of new sub calculations$")
	public void i_loop_a_series_of_new_sub_calculations() throws Throwable {
		for(int i = 1000;i>1;i=i-5) {
			int x = i/2;
			service.executeGetRequest("http://localhost:8080/sub?value1="+i+"&value2="+x);
			System.out.println(i+ " - " +x+ " = "+(i-x));
			assertEquals(Integer.toString(i-x), service.executeGetRequest("http://localhost:8080/sub?value1="+i+"&value2="+x));
		}
	}
	@When("^I loop a series of new mult calculations$")
	public void i_loop_a_series_of_new_mult_calculations() throws Throwable {
		for(int i = 0;i<1000;i=i+4) {
			int x = i/2;
			service.executeGetRequest("http://localhost:8080/mult?value1="+i+"&value2="+x);
			System.out.println(i+ " * " +x+ " = "+(i*x));
			assertEquals(Integer.toString(i*x), service.executeGetRequest("http://localhost:8080/mult?value1="+i+"&value2="+x));
		}
	}
	@When("^I loop a series of new div calculations$")
	public void i_loop_a_series_of_new_div_calculations() throws Throwable {
		/*for(int i = 0;i<1000;i=i+5) {
			int x = i/2;
			service.executeGetRequest("http://localhost:8080/add?value1="+i+"&value2="+x);
			System.out.println(i+ " + " +x+ " = "+i+x);
			assertEquals(Integer.toString(i+x), service.executeGetRequest("http://localhost:8080/add?value1="+i+"&value2="+x));
		}*/
	}
	@Then("^Mimic is responding with correct sum in each loop$")
	public void mimic_is_responding_with_correct_sum_in_each_loop() throws Throwable {
	}
	
	
	// @Test_Case_ID_21
	
	// @Test_Case_ID_22
	
	// @Test_Case_ID_23
	
	// @Test_Case_ID_24
	
}
