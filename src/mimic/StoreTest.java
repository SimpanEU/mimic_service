package mimic;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class StoreTest {

	@Test
	void test() {
		
		Store store = new Store();
		
		/*
		store.learnResponse(String request, String response);
		store.getResponse(String request);
		store.unlearnAll();
		*/

		assertEquals(store.learnResponse(x, x), x);
		assertEquals(store.getResponse(x), x);
		assertEquals(store.unlearnAll(), x);
		
		
		
	}

}