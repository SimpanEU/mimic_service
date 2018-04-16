package mimic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

class StoreTest {
	
	/*
	 * ID 1
	 * Tests learnResponse function is working by giving "apple" and "fruit" as parameters and calling for getResponse.
	 * Expected return should be "fruit"
	 */
	@Test
	void test_learnAndGetResponse() {
		Store store = new Store();
		store.learnResponse("apple", "fruit");
		assertEquals(store.getResponse("apple"), "fruit");
	}

	
	/*
	 * ID 2
	 * Tests getResponse function is giving correct return when being asked for an unsaved entry.
	 * Expected return should be "null"
	 */
	@Test
	void test_getResponseWithoutPrevRecord() {
		Store store = new Store();
		assertEquals(store.getResponse("banana"), null);
		assertEquals(store.getResponse(""), null);
	}
	
	
	/*
	 * ID 3
	 * Tests unlearnAll function is working by first learning different responses, ask for learned responses, unlearn the responses and then ask again.
	 * Expected return should be "null"
	 */
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
	
	
	/*
	 * ID 4
	 * Tests that learnResponse and getResponse functions is not returning a wrong value.
	 * Expected return is not null.
	 */
	@Test
	void test_learnAndGetNotMatchingResponse() {
		Store store = new Store();
		store.learnResponse("apple", "apple");
		assertNotEquals(store.getResponse("apple"), null);
	}
	
	
	
}