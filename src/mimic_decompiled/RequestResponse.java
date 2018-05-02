package mimic;

import java.io.Serializable;

public class RequestResponse implements Serializable
{
  private static final long serialVersionUID = -1122726896151657411L;
  private String request;
  private String response;
  private String responseMime;
  private boolean isUsed = false;
  
  public RequestResponse(String request, String response, String responseMime)
  {
    this.request = request;
    this.response = response;
    this.responseMime = responseMime;
  }
  
  public String getRequest()
  {
    return request;
  }
  
  public void setRequest(String request)
  {
    this.request = request;
  }
  
  public String getResponse()
  {
    return response;
  }
  
  public void setResponse(String response)
  {
    this.response = response;
  }
  
  public String getResponseMime()
  {
    return responseMime;
  }
  
  public void setResponseMime(String responseMime)
  {
    this.responseMime = responseMime;
  }
  
  public boolean isUsed()
  {
    return isUsed;
  }
  
  public void setUsed(boolean isUsed)
  {
    this.isUsed = isUsed;
  }
}
