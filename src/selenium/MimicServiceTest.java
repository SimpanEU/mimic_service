package selenium;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import selenium.HttpServiceCaller;

public class MimicServiceTest {
	
	
	@Test
	public void test() {
		MimicService mimictest = new MimicService();
		mimictest.mimicservice();
	

		HttpServiceCaller service = new HttpServiceCaller();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(service.executeGetRequest("http://localhost:8080/request"), "res2");
		
		
		
	}
	
}