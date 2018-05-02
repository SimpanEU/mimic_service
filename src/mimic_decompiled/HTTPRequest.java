package mimic;

import java.io.InputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Vector;


public class HTTPRequest
{
  public static final int REQUEST_TYPE_INVALID = -1;
  public static final int REQUEST_TYPE_GET = 0;
  public static final int REQUEST_TYPE_POST = 1;
  public static final int REQUEST_TYPE_PUT = 2;
  public static final int REQUEST_TYPE_PATCH = 3;
  public static final int REQUEST_TYPE_DELETE = 4;
  private int requestType = -1;
  private String httpVersion = "";
  private String request = "";
  private String command = "";
  private String commandParamString = "";
  private Vector commandParams = new Vector();
  private Properties requestParams = new Properties();
  private String commandAndParams = "";
  private byte[] body = null;
  

  HTTPRequest() {}
  

  public String getCommand()
  {
    return command;
  }
  
  public void setCommand(String command)
  {
    this.command = command;
  }
  
  public String getHttpVersion()
  {
    return httpVersion;
  }
  
  public int getRequestType()
  {
    return requestType;
  }
  







  public static String createRequest(String request)
  {
    int startOfParams = request.indexOf('?');
    String params; String command; String params; if (startOfParams == -1)
    {
      String command = request;
      params = "";
    }
    else
    {
      command = request.substring(0, startOfParams);
      params = request.substring(startOfParams + 1);
    }
    
    StringBuffer http = new StringBuffer();
    

    http.append("POST /");
    http.append(command);
    http.append(" HTTP/1.1\r\n");
    http.append("Content-Length: ");
    http.append(params.length());
    http.append("\r\n");
    http.append("Content-Type: text/xml\r\n\r\n");
    http.append(params);
    
    return http.toString();
  }
  





  public boolean readRequest(String request)
  {
    this.request = request;
    Vector commandAndParamsArray = splitString(request, '?');
    command = decode((String)commandAndParamsArray.elementAt(0));
    if (commandAndParamsArray.size() >= 2)
    {

      commandParamString = ((String)commandAndParamsArray.elementAt(1));
      commandParams = parseParameters(commandParamString);
    }
    return true;
  }
  




  public boolean readRequest(Socket socket)
  {
    try
    {
      InputStream inputStream = socket.getInputStream();
      
      String header = readLine(inputStream);
      if (header == null)
      {

        return false;
      }
      
      request = header;
      

      if (header.length() < 4)
      {

        return false;
      }
      

      requestType = getHttpRequestType(header);
      if (requestType == -1)
      {

        return false;
      }
      
      httpVersion = getHttpVersion(header);
      if (httpVersion == null)
      {
        return false;
      }
      
      if (!readRequestParameters(inputStream))
      {

        return false;
      }
      
      int startCommandIndex = 4;
      if ((requestType == 0) || (requestType == 2))
      {
        startCommandIndex = 4;
      }
      else if (requestType == 1)
      {
        startCommandIndex = 5;
      }
      else if (requestType == 3)
      {
        startCommandIndex = 6;
      }
      else if (requestType == 4)
      {
        startCommandIndex = 7;
      }
      
      commandAndParams = getHttpCommand(header, startCommandIndex);
      if (commandAndParams == null) {
        return false;
      }
      Vector<String> commandAndParamsArray = splitString(commandAndParams, '?');
      command = decode((String)commandAndParamsArray.elementAt(0));
      if (commandAndParamsArray.size() >= 2)
      {

        commandParamString = ((String)commandAndParamsArray.elementAt(1));
        commandParams = parseParameters(commandParamString);
      }
      
      if ((requestType == 1) || (requestType == 2) || (requestType == 3))
      {

        int length = getContentLength();
        body = readBody(inputStream, length);
      }
      
      return true;
    }
    catch (Exception e) {}
    
    return false;
  }
  






  private int getContentLength()
  {
    String propContentLength = requestParams.getProperty("Content-Length");
    if (propContentLength != null) {
      return string2Int(propContentLength);
    }
    propContentLength = requestParams.getProperty("Content-length");
    if (propContentLength != null) {
      return string2Int(propContentLength);
    }
    propContentLength = requestParams.getProperty("content-length");
    if (propContentLength != null) {
      return string2Int(propContentLength);
    }
    return 0;
  }
  





  private String getContentType()
  {
    String propContentType = requestParams.getProperty("Content-Type");
    if (propContentType != null) {
      return propContentType;
    }
    propContentType = requestParams.getProperty("Content-type");
    if (propContentType != null) {
      return propContentType;
    }
    propContentType = requestParams.getProperty("content-type");
    if (propContentType != null) {
      return propContentType;
    }
    return "";
  }
  



  private String readLine(InputStream inStream)
  {
    ByteBuffer bytes = new ByteBuffer();
    

    try
    {
      for (;;)
      {
        int nextChar = inStream.read();
        if (nextChar == -1)
        {

          byte[] b = bytes.getByteArray();
          
          return new String(b, "UTF-8");
        }
        
        if ((char)nextChar != '\r')
        {


          if ((char)nextChar == '\n')
          {

            byte[] b = bytes.getByteArray();
            
            return new String(b, "UTF-8");
          }
          



          bytes.addByte((byte)nextChar);
        }
      }
      


      return "";
    }
    catch (Exception e) {}
  }
  





  private byte[] readBody(InputStream inStream, int length)
  {
    ByteBuffer bytes = new ByteBuffer();
    
    try
    {
      for (int i = 0; i < length; i++)
      {

        int nextChar = inStream.read();
        bytes.addByte((byte)nextChar);
      }
      return bytes.getByteArray();
    }
    catch (Exception e) {}
    

    return null;
  }
  






  private String getHttpCommand(String message, int startIndex)
  {
    int first = findCommandIndex(message, startIndex);
    if (first == -1) {
      return "";
    }
    String command = "";
    int last = message.indexOf("HTTP/", first);
    if (last == -1) {
      command = message.substring(first);
    } else
      command = message.substring(first, last);
    return command.trim();
  }
  



  private String getHttpVersion(String message)
  {
    int last = message.indexOf("HTTP/");
    if (last == -1)
      return null;
    return message.substring(last, last + 8);
  }
  



  private int getHttpRequestType(String header)
  {
    if (header.startsWith("GET"))
    {

      return 0;
    }
    if (header.startsWith("POST"))
    {

      return 1;
    }
    if (header.startsWith("PUT"))
    {

      return 2;
    }
    if (header.startsWith("PATCH"))
    {

      return 3;
    }
    if (header.startsWith("DELETE"))
    {

      return 4;
    }
    


    return -1;
  }
  




  private int findCommandIndex(String header, int startIndex)
  {
    int endIndex = header.indexOf("HTTP/");
    if (endIndex == -1)
      endIndex = header.length();
    for (int i = startIndex; i < endIndex; i++)
    {
      char c = header.charAt(i);
      if ((c != ' ') && (c != '/'))
      {

        return i;
      }
    }
    
    return -1;
  }
  



  private boolean readRequestParameters(InputStream inStream)
  {
    requestParams = new Properties();
    

    try
    {
      for (;;)
      {
        String newLine = readLine(inStream);
        if ((newLine == null) || ("".equals(newLine)))
        {

          return true;
        }
        


        int delimiter = newLine.indexOf(':');
        if (delimiter != -1)
        {





          String key = newLine.substring(0, delimiter);
          String value = newLine.substring(delimiter + 1);
          value = value.trim();
          requestParams.setProperty(key, value);
        }
      }
      



      return false;
    }
    catch (Exception e) {}
  }
  


  private Vector parseParameters(String param)
  {
    Vector p = new Vector();
    
    Vector paramArray = splitString(param, '&');
    for (int i = 0; i < paramArray.size(); i++)
    {
      Vector propArray = splitString(decode((String)paramArray.elementAt(i)), "=");
      if (propArray.size() == 2)
      {

        String key = (String)propArray.elementAt(0);
        String value = (String)propArray.elementAt(1);
        KeyValue commandParameter = new KeyValue(key, value);
        p.add(commandParameter);
      }
      else if (propArray.size() == 1)
      {

        String key = (String)propArray.elementAt(0);
        String value = null;
        if ((key != null) && (!"".equals(key)))
        {

          KeyValue commandParameter = new KeyValue(key, value);
          p.add(commandParameter);
        }
      }
    }
    return p;
  }
  



  public String getParameter(String key)
  {
    for (int i = 0; i < commandParams.size(); i++)
    {
      KeyValue keyValue = (KeyValue)commandParams.elementAt(i);
      if (key.equalsIgnoreCase(keyValue.getKey()))
        return keyValue.getValue();
    }
    return null;
  }
  



  public Properties getParameters()
  {
    Properties p = new Properties();
    
    for (int i = 0; i < commandParams.size(); i++)
    {
      KeyValue keyValue = (KeyValue)commandParams.elementAt(i);
      p.setProperty(keyValue.getKey(), keyValue.getValue());
    }
    return p;
  }
  



  public void setParameter(String key, String value)
  {
    for (int i = 0; i < commandParams.size(); i++)
    {
      KeyValue keyValue = (KeyValue)commandParams.elementAt(i);
      if (key.equals(keyValue.getKey()))
      {

        keyValue = new KeyValue(key, value);
        commandParams.setElementAt(keyValue, i);
        return;
      }
    }
    addParameter(key, value);
  }
  



  public boolean addParameter(String key, String value)
  {
    try
    {
      KeyValue keyValue = new KeyValue(key, value);
      commandParams.add(keyValue);
    }
    catch (Throwable e)
    {
      return false;
    }
    return true;
  }
  




  public String getCommandString()
  {
    StringBuffer buf = new StringBuffer();
    
    buf.append(encode(command));
    buf.append("?");
    buf.append(getCommandParamString());
    return decode(buf.toString());
  }
  





  public String getCommandParamString()
  {
    return decode(commandParamString);
  }
  



  public static Vector<String> splitString(String s, char delimiter)
  {
    Vector<String> v = new Vector();
    StringBuffer b = new StringBuffer();
    for (int i = 0; i < s.length(); i++)
    {
      char nextChar = s.charAt(i);
      if (nextChar == delimiter)
      {

        v.add(b.toString());
        b = new StringBuffer();
      }
      else
      {
        b.append(nextChar);
      }
    }
    
    v.add(b.toString());
    return v;
  }
  
  public static int string2Int(String s)
  {
    return string2Int(s, 0);
  }
  
  public static int string2Int(String s, int otherwise)
  {
    try
    {
      return Integer.parseInt(s);
    }
    catch (Exception e) {}
    

    return otherwise;
  }
  









  public static Vector splitString(String s, String delimiter)
  {
    Vector v = new Vector();
    for (int i = 0; i < s.length(); i++)
    {
      int pos = s.indexOf(delimiter);
      if (pos > -1)
      {

        v.add(s.substring(0, pos));
        if (pos + delimiter.length() >= s.length()) {
          v.add("");
        } else
          v.add(s.substring(pos + delimiter.length()));
        return v;
      }
    }
    v.add(s);
    return v;
  }
  



  public static String decode(String s)
  {
    try
    {
      return URLDecoder.decode(s, "UTF-8");
    }
    catch (Exception e) {}
    
    return "";
  }
  

  public static String encode(String s)
  {
    try
    {
      return URLEncoder.encode(s, "UTF-8");
    }
    catch (Exception e) {}
    
    return "";
  }
  

  public byte[] getBody()
  {
    return body;
  }
  
  public void setBody(byte[] body)
  {
    this.body = body;
  }
  
  public Properties getRequestParams()
  {
    return requestParams;
  }
  
  public String getRequest()
  {
    return request;
  }
}
