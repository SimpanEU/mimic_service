package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

public class MimicServiceTest {
	
	private MimicService mimic = new MimicService();
	private HttpServiceCaller service = new HttpServiceCaller();
	
	String request = "http://localhost:8080/request";	
	
	@Test
	public void test() {
		mimic.mimicservice();
		mimic.sleep(500);
		assertEquals(service.executeGetRequest(request), "res1");
		assertEquals(service.executeGetRequest(request), "res2");
		assertNotEquals(service.executeGetRequest(request), "res3");
		assertNotEquals(service.executeGetRequest(request), "res4");
		assertNotEquals(service.executeGetRequest(request), "res5");
		mimic.quit();
	}

}