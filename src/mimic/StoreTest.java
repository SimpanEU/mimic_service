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
	void test_response() {
		
		Store store = new Store();
		
		
		store.learnResponse("apple", "fruit");
		store.getResponse("apple");
		
		assertEquals(store.getResponse("apple"), "fruit");
	}
	@Test
	void test_response_ifIsEmpty() {
		Store store = new Store();
		
		store.getResponse("banana");
		
		//assertEquals(store.getResponse("banana"),null);
	}
	
	
	/*
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
	*/

}