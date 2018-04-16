package mimic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
		assertEquals(store.getResponse("apple"), "fruit");
	}

	@Test
	void test_getResponseWithoutPrevRecord() {
		Store store = new Store();
		assertEquals(store.getResponse("banana"), null);
		assertEquals(store.getResponse(""), null);
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
	
	@Test
	void test_learnAndGetNotMatchingResponse() {
		Store store = new Store();
		store.learnResponse("apple", "apple");
		assertNotEquals(store.getResponse("apple"), "banana");
	}
	
	
	
}