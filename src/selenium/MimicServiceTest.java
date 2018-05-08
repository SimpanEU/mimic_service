package selenium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class MimicServiceTest {
	
	private MimicService mimic = new MimicService();
	private HttpServiceCaller service = new HttpServiceCaller();
	
	@Test
	public void test() {
		mimic.mimicservice();
		mimic.sleep(500);
		assertEquals(service.executeGetRequest("http://localhost:8080/request"), "res2");
		mimic.quit();
	}

}