package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class MimicServiceTest {
	
	private MimicService mimic = new MimicService();
	private HttpServiceCaller service = new HttpServiceCaller();
	
	@Test
	public void test() {
		mimic.mimicservice();
		mimic.sleep(500);
		assertEquals(service.executeGetRequest("http://localhost:8080/request"), "res1");
		assertEquals(service.executeGetRequest("http://localhost:8080/request"), "res2");
		assertNotEquals(service.executeGetRequest("http://localhost:8080/request"), "res3");
		assertNotEquals(service.executeGetRequest("http://localhost:8080/request"), "res4");
		assertNotEquals(service.executeGetRequest("http://localhost:8080/request"), "res5");
		mimic.sleep(1000);
	//sprint 5	
		mimic.mimicservice2();
		mimic.sleep(500);
		assertEquals(service.executeGetRequest("http://localhost:8080/request"), "res1");
		assertEquals(service.executeGetRequest("http://localhost:8080/request"), "newresponse1");
		assertEquals(service.executeGetRequest("http://localhost:8080/request"), "newresponse2");
		assertNotEquals(service.executeGetRequest("http://localhost:8080/request"), "res3");
		assertNotEquals(service.executeGetRequest("http://localhost:8080/request"), "res4");
		assertNotEquals(service.executeGetRequest("http://localhost:8080/request"), "res5");
		mimic.sleep(1000);
	//sprint 6	
		mimic.mimicservice3();
		mimic.sleep(500);
		assertEquals(service.executeGetRequest("http://localhost:8080/test?param"), "testparam");
		assertEquals(service.executeGetRequest("http://localhost:8080/test/testar?hello"), "hello");
		assertEquals(service.executeGetRequest("http://localhost:8080/UnlearnResponse"), "1 responses unlearned");
		assertEquals(service.executeGetRequest("http://localhost:8080/resetState"), "State reset");
		mimic.sleep(1000);
		
		mimic.mimicservice4();
		mimic.sleep(500);
		assertEquals(service.executeGetRequest("http://localhost:8080/newrequest"), "newresponse");
		assertThat(service.executeGetRequest("http://localhost:8080/learnNextResponse"), containsString("Paste or type json, xml, html or text response to learn and press Learn"));

		mimic.quit();
	}

	
}
	
	
	
	
	
	