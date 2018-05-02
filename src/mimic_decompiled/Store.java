package mimic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Store implements Serializable
{
  private static final long serialVersionUID = -3281550257641468942L;
  private List<RequestResponse> responses = new ArrayList();
  


  public Store() {}
  


  public void learnResponse(String request, String response, String responseMime)
  {
    responses.add(new RequestResponse(request, response, responseMime));
  }
  




  public RequestResponse getResponse(String request)
  {
    RequestResponse lastResponse = null;
    for (RequestResponse response : responses)
    {
      if (response.getRequest().equals(request))
      {
        lastResponse = response;
        if (!response.isUsed())
        {
          return response;
        }
      }
    }
    return lastResponse;
  }
  




  public void unlearn(String request)
  {
    RequestResponse response = getResponse(request);
    if (response != null)
    {
      responses.remove(response);
    }
  }
  



  public void unlearnAll()
  {
    responses = new ArrayList();
  }
  



  public void resetState()
  {
    for (RequestResponse response : responses)
    {
      response.setUsed(false);
    }
  }
}
