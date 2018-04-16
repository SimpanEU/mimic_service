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
	void test_learnAndGetResponse() {
		Store store = new Store();
		store.learnResponse("apple", "fruit");
		store.getResponse("apple");
		assertEquals(store.getResponse("apple"), "fruit");
	}

	
	@Test
	void test_getResponseWithoutPrevRecord() {
		Store store = new Store();
		store.getResponse("banana");
		assertEquals(store.getResponse("banana"), null);
	}
	
	
	@Test
	void test_unlearnAll() {
		Store store = new Store();
		store.learnResponse("apple", "red fruit");
		store.learnResponse("banana", "yellow fruit");
		store.learnResponse("pear", "green fruit");
		assertEquals(store.getResponse("apple"),"red fruit");
		assertEquals(store.getResponse("banana"), "yellow fruit");
		assertEquals(store.getResponse("pear"), "green fruit");
		store.unlearnAll();
		assertEquals(store.getResponse("apple"), null);
		assertEquals(store.getResponse("banana"), null);
		assertEquals(store.getResponse("pear"), null);
		
	}
}