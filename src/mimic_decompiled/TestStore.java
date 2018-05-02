package mimic;

import org.junit.jupiter.api.Test;

class TestStore
{
  TestStore() {}
  
  @Test
  void test()
  {
    Store store = new Store();
    store.learnResponse("getIssues", "<issues></issues>", "application/xml");
    store.learnResponse("getIssue", "<issue></issue>", "application/xml");
    RequestResponse response = store.getResponse("getIssues");
    org.junit.jupiter.api.Assertions.assertEquals("<issues></issues>", response.getResponse());
  }
}
