package mimic;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class StoreTest {

	/*
	store.learnResponse(String request, String response);
	store.getResponse(String request);
	store.unlearnAll();
	*/
	
	@Test
	void test_learnResponse() {
		
		Store store = new Store();
		
		assertEquals(store.learnResponse(x, x), x);
	}
		
	@Test
	void test_getResponse() {
		Store store = new Store();
		
		assertEquals(store.getResponse(x), x);
	}
	
	@Test
	void test_unlearnAll() {
		Store store = new Store();
		
		assertEquals(store.unlearnAll(), x);
	}

}