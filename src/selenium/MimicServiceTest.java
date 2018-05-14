package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

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
		
		mimic.quit();
	}

	
}
	
	
	
	
	
	