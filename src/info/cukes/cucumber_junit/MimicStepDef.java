package info.cukes.cucumber_junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MimicStepDef {
	
	private final static String host = "http://localhost:8080/";
	private HttpServiceCaller service=new HttpServiceCaller();
	private String response;
	private String placeholder;
	

	@Given("^mimic\\.jar is running$")
	public void mimic_jar_is_running() throws Throwable {
		if(!(service.executeGetRequest("http://localhost:8080/error").equals("Paste or type json, xml, html or text response to learn and press Learn<br><br><form action=\"learn\" method=\"post\"><textarea name='text' rows='30' cols='150'></textarea><br><br><input type=\"submit\" id='learn' value=\"Learn\"></form>"))) {
			// Launches mimic.jar from your C:/Users/<username> folder.
			System.out.println("Service offline. Starting mimic.jar ...");
			String user = System.getProperty("user.name");
			String cmd = "java -jar C://Users//" + user + "//mimic.jar";
			Runtime.getRuntime().exec(cmd);
		}
		
		// service.executeGetRequest("http://localhost:8080/unlearnAll");
		
		/*
		// Tests if LearnNextResponse method returns "OK".
		String request=host+"LearnNextResponse?text=TestIfRunning";
		String response=service.executeGetRequest(request);
		assertEquals(response, "OK"); java -jar C://Users//Simp//mimic.jar
		*/
	}

	
	@When("^I learn a response$")
	public void i_learn_a_response() throws Throwable {
		String request = host + "LearnNextResponse?text=fruit" ;
		String response = service.executeGetRequest(request);
	}
	
	@When("^I learn a request$")
	public void i_learn_a_request() throws Throwable {
		String request = host + "apple" ;
		service.executeGetRequest(request);
	}
	
	@Then("^mimic\\.jar is responding with correct response$")
	public void mimic_jar_is_responding_with_correct_response() throws Throwable {
		String request = host + "apple" ;
		String response = service.executeGetRequest(request);
		assertEquals(response, "fruit");		
	}
	
	@When("^I unlearn the previous request/response using the \"([^\"]*)\" command$")
	public void i_unlearn_the_previous_request_response_using_the_command(String arg1) throws Throwable {
		String request=host+"unlearn";
		String response=service.executeGetRequest(request);
		assertEquals(response, "OK");
	}
	
	@Then("^Previous request/response is removed$")
	public void previous_request_response_is_removed() throws Throwable {
		String request=host+"apple";
		String response=service.executeGetRequest(request);
		assertNotEquals(response, "fruit");
	}

	@When("^I use the mimic\\.jar shutdown function$")
	public void i_use_the_mimic_jar_shutdown_function() throws Throwable {
		
		// Triggers KillMimic method and shuts down the service. Expected return is "OK".
		String request=host+"KillMimic";
		String response=service.executeGetRequest(request);
		assertEquals(response, "OK");
	}

	@Then("^mimic\\.jar is not running$")
	public void mimic_jar_is_not_running() throws Throwable {
		
		// Calls for KillMimic method once again, this time we do not expect any "OK" as the service should already been shutdown.
		String request=host+"KillMimic";
		String response=service.executeGetRequest(request);
		Thread.sleep(1000);
		response=service.executeGetRequest(request);
		assertNotEquals(response, "OK");
		
		/*
		// Re-launches the mimic.jar after its being shutdown, requires you to have the mimic.jar in your C:/Users/<username> folder.
		String user = System.getProperty("user.name");
		String cmd = "java -jar C://Users//" + user + "//mimic.jar";
		Runtime.getRuntime().exec(cmd); */
	}
	@When("^I learn a new response$")
	public void i_learn_a_new_response() throws Throwable {
		service.executeGetRequest(host + "apple");
		service.executeGetRequest(host + "relearn");
		String request = host + "LearnNextResponse?text=fruity" ;
		String response = service.executeGetRequest(request);
	}

	@Then("^mimic\\.jar is responding with correct new response$")
	public void mimic_jar_is_responding_with_correct_new_response() throws Throwable {
		String request = host + "apple" ;
		String response = service.executeGetRequest(request);
		assertEquals(response, "fruity");
	}
	
	@When("^I learn a \"([^\"]*)\" and a \"([^\"]*)\"$")
	public void i_learn_a_and_a(String arg1, String arg2) throws Throwable {
		String request = host + "LearnNextResponse?text=" + arg2;
		service.executeGetRequest(request);
		String response = service.executeGetRequest("http://localhost:8080/" +arg1);
		service.executeGetRequest("http://localhost:8080/" +arg1);
		this.placeholder = response;
	}
	
	@Then("^mimic\\.jar is responding with correct \"([^\"]*)\"$")
	public void mimic_jar_is_responding_with_correct(String arg1) throws Throwable {
		String request = host + arg1 ;
		String response = service.executeGetRequest(request);
		assertEquals(service.executeGetRequest(request), this.placeholder);
	}

	@When("^I learn a (\\d+) and a (\\d+)$")
	public void i_learn_a_and_a(int arg1, int arg2) throws Throwable {
		String request = host + "LearnNextResponse?text=" + arg2 ;
		String response = service.executeGetRequest(request);
		
		String request_again = host + arg1;
		String response_again = service.executeGetRequest(request_again);
		this.placeholder = Integer.toString(arg2);
	}

	@Then("^mimic\\.jar is responding with correct (\\d+)$")
	public void mimic_jar_is_responding_with_correct(int arg1) throws Throwable {
		String request = host + arg1 ;
		String response = service.executeGetRequest(request);
		assertEquals(response, this.placeholder);
	}
	
	
	
	
	
	@When("^I learn a video response$")
	public void i_learn_a_video_response() throws Throwable {
		String request = host + "LearnNextResponse?text=<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/IcrbM1l_BoI\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>&mime=text/html";		
		String response = service.executeGetRequest(request);
	}
	@When("^I learn a video request$")
	public void i_learn_a_video_request() throws Throwable {
		String request = host + "avicii" ;
		String response = service.executeGetRequest(request);
	}
	@Then("^mimic\\.jar is responding with correct video response$")
	public void mimic_jar_is_responding_with_correct_video_response() throws Throwable {
		String request = host + "avicii" ;
		String response = service.executeGetRequest(request);
		assertEquals(response, "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/IcrbM1l_BoI\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>");
	}
	
//2018-4-30 MONDAY-UserStory 6
//Testing if unlearnAll function can remove all the responses
		
	@When("^I unlearn the previous responses using the \"([^\"]*)\" command$")
	public void i_unlearn_the_previous_responses_using_the_command(String arg1) throws Throwable {
		String request=host+"unlearnAll";
		String response=service.executeGetRequest(request);
		assertEquals(response, "OK");
	}

	@Then("^All previous responses are removed$")
	public void all_previous_responses_are_removed() throws Throwable {
		String request=host+"banana";
		String response=service.executeGetRequest(request);
		assertNotEquals(response, "0000");
	}

	//Testing to re-run all the requests to see if all the responses are removed. 

	@When("^I run the previous \"([^\"]*)\" again$")
	public void i_run_the_previous_again(String arg1) throws Throwable {
		String request = host + arg1;
		String response = service.executeGetRequest(request);
		this.response = response;

	}

	@When("^I run the previous (\\d+) again$")
	public void i_run_the_previous_again(int arg1) throws Throwable {
		String request = host + arg1;
		String response = service.executeGetRequest(request);
		this.response = response;

	}


	@Then("^Previous \"([^\"]*)\" are removed$")
	public void previous_are_removed(String arg1) throws Throwable {
		String response= arg1;
		assertNotEquals(response, this.response);

	}

	@Then("^Previous (\\d+) are removed$")
	public void previous_are_removed(int arg1) throws Throwable {
		String response = Integer.toString(arg1);
		assertNotEquals(response, this.response);
	}
	
	
	
	@When("^mimic\\.jar is launched$")
	public void mimic_jar_is_launched() throws Throwable {
		String user = System.getProperty("user.name");
		String cmd = "java -jar C://Users//" + user + "//mimic.jar";
		Runtime.getRuntime().exec(cmd);
	}

	@Then("^\"([^\"]*)\" is returning \"([^\"]*)\"$")
	public void is_returning(String arg1, String arg2) throws Throwable {
		String request = host + arg1 ;
		String response = service.executeGetRequest(request);
		System.out.println("arg 1 request =" + arg1);
		System.out.println("arg 2 response =" + arg2);
		this.placeholder = response;
		assertEquals(arg2, this.placeholder);
	}
	
	
	
	
	
	
	
	
	@When("^I use resetState function$")
	public void i_use_resetState_function() throws Throwable {
		service.executeGetRequest("http://localhost:8080/resetState");
	}

	@Then("^\"([^\"]*)\" returns correct \"([^\"]*)\"$")
	public void returns_correct(String arg1, String arg2) throws Throwable {
		
		String request = service.executeGetRequest("http://localhost:8080/" +arg1);
		String response = arg2;
		assertEquals(request, response);
		
	}

}
