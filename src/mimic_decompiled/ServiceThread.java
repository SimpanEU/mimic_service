package mimic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceThread
  extends Thread
{
  private PrintStream outStream;
  private Socket socket;
  private static Mimic mimic;
  private static final int BUFFER = 10000;
  private static final int SAVE_INTERVAL = 1800;
  private static Store store = null;
  private static String nextResponseText = null;
  private static String nextResponseMime = null;
  private static String lastRequest = null;
  
  private static SimpleDateFormat simpleRecordingDateTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
  private static SimpleDateFormat httpDateTime = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
  
  ServiceThread(Socket socket, Mimic eyeServer)
  {
    this.socket = socket;
    mimic = eyeServer;
    
    if (store == null)
    {
      store = (Store)loadObject("brain");
      if (store == null)
      {

        store = new Store();
      }
    }
  }
  
  public void run()
  {
    processRequest();
  }
  

  private void processRequest()
  {
    try
    {
      BufferedOutputStream bufferedOutStream = new BufferedOutputStream(socket.getOutputStream());
      outStream = new PrintStream(bufferedOutStream, false, "UTF-8");
    }
    catch (Exception e)
    {
      System.out.println("Failed to read command: " + e.toString());
      return;
    }
    try
    {
      BufferedOutputStream bufferedOutStream;
      HTTPRequest request = new HTTPRequest();
      
      if (!request.readRequest(socket))
      {
        System.out.println("Failed to read command");
        return;
      }
      
      String command = request.getCommand().trim();
      
      if (command.endsWith(".png"))
      {

        sendMediaResponse(command, "image/png");
        return;
      }
      if (command.endsWith(".ico"))
      {

        sendMediaResponse(command, "image/x-icon");
        return;
      }
      if ("killmimic".equalsIgnoreCase(command))
      {
        sendResponse("OK");
        if (store != null)
        {
          saveObject("brain", store);
        }
        mimic.stopServer();
        return;
      }
      if ("learnnextresponse".equalsIgnoreCase(command))
      {
        String text = request.getParameter("text");
        String mime = request.getParameter("mime");
        
        if (text == null)
        {
          sendResponse("Provide text");
          return;
        }
        if (mime == null)
        {
          mime = detectMime(text);
        }
        
        nextResponseText = text;
        nextResponseMime = mime;
        
        sendResponse("OK");
        return;
      }
      if ("unlearn".equalsIgnoreCase(command))
      {
        if (lastRequest != null)
        {
          store.unlearn(lastRequest);
          sendResponse("OK");
          return;
        }
        

        sendResponse("Nothing to unlearn");
        return;
      }
      
      if ("unlearnall".equalsIgnoreCase(command))
      {
        store.unlearnAll();
        sendResponse("OK");
        return;
      }
      if ("resetstate".equalsIgnoreCase(command))
      {
        store.resetState();
        sendResponse("OK");
        return;
      }
      if ("relearn".equalsIgnoreCase(command))
      {
        if (lastRequest != null)
        {
          String reponseForm = createReponseForm();
          sendResponse(reponseForm);
          return;
        }
        

        sendResponse("Nothing to relearn");
        return;
      }
      
      if ("learn".equalsIgnoreCase(command))
      {
        if (request.getRequestType() == 1)
        {
          String text = HTTPRequest.decode(new String(request.getBody(), "UTF-8")).trim();
          if (text.startsWith("text="))
          {
            text = text.substring(5);
            

            String mime = detectMime(text);
            
            if (lastRequest == null)
            {
              sendResponse("No request");
              return;
            }
            if (text == null)
            {
              sendResponse("Provide text");
              return;
            }
            if (mime == null)
            {
              mime = "text/html";
            }
            
            store.learnResponse(lastRequest, text, mime);
            


            sendResponse("OK");
          }
          
        }
      }
      else
      {
        lastRequest = request.getRequest();
        if ((nextResponseText != null) && (nextResponseMime != null))
        {

          store.learnResponse(lastRequest, nextResponseText, nextResponseMime);
          sendResponse(nextResponseText, nextResponseMime, true);
          nextResponseText = null;
          nextResponseMime = null;
        }
        else
        {
          RequestResponse response = store.getResponse(lastRequest);
          if (response == null)
          {

            String reponseForm = createReponseForm();
            sendResponse(reponseForm);

          }
          else
          {
            response.setUsed(true);
            sendResponse(response.getResponse(), response.getResponseMime(), true);
          }
        }
        return;
      }
    }
    catch (Exception e)
    {
      System.out.println("Failed to read command: " + e.toString());
      return;
    }
  }
  

  private String createHttpResponse(String data, String contentType)
  {
    StringBuffer http = new StringBuffer();
    

    try
    {
      dataBytes = data.getBytes("UTF-8");
    }
    catch (Exception e)
    {
      byte[] dataBytes;
      return null;
    }
    
    byte[] dataBytes;
    http.append("HTTP/1.1 200 OK\r\n");
    http.append("Date: ");
    http.append(createHTTPDateString());
    http.append("\r\n");
    http.append("Server: Mimic Web Service\r\n");
    http.append("Last-Modified: ");
    http.append(createHTTPDateString());
    http.append("\r\n");
    http.append("Accept-Ranges: bytes\r\n");
    http.append("ETag: \"");
    http.append(createHTTPDateString());
    http.append("\"\r\n");
    http.append("Content-Length: ");
    http.append(dataBytes.length);
    http.append("\r\n");
    http.append("Connection: close\r\n");
    http.append("Content-Type: ");
    http.append(contentType);
    http.append("\r\n\r\n");
    

    http.append(data);
    
    return http.toString();
  }
  
  private void createHttpMediaResponse(String mediaFile, String mimeType, PrintStream out)
  {
    try
    {
      int length = (int)getFileLength(mediaFile);
      byte[] dest = null;
      if (length > 0)
      {

        dest = new byte[length];
        length = getMediaFile(mediaFile, dest);
      }
      
      StringBuffer http = new StringBuffer();
      

      if (length <= 0)
      {

        http.append("HTTP/1.1 404 Not Found\r\n");
      }
      else
      {
        http.append("HTTP/1.1 200 OK\r\n");
      }
      http.append("Date: ");
      http.append(createHTTPDateString());
      http.append("\r\n");
      
      http.append("Server: JAutomate Web Service\r\n");
      
      http.append("ETag: ");
      http.append(createChecksum(dest));
      http.append("\r\n");
      
      http.append("Content-Length: ");
      if (length < 0)
      {
        http.append("0");
      }
      else
      {
        http.append(length);
      }
      http.append("\r\n");
      http.append("Connection: close\r\n");
      http.append("Content-Type: ");
      http.append(mimeType);
      http.append("\r\n\r\n");
      

      out.write(http.toString().getBytes());
      if ((length > 0) && (dest != null))
      {

        out.write(dest, 0, length);
      }
    }
    catch (Exception e) {}
  }
  



  private void createHttpMediaResponse(byte[] dest, String mimeType, PrintStream out)
  {
    try
    {
      int length = dest.length;
      
      StringBuffer http = new StringBuffer();
      

      if (length <= 0)
      {

        http.append("HTTP/1.1 404 Not Found\r\n");
      }
      else
      {
        http.append("HTTP/1.1 200 OK\r\n");
      }
      http.append("Date: ");
      http.append(createHTTPDateString());
      http.append("\r\n");
      
      http.append("Server: JAutomate Web Service\r\n");
      
      http.append("ETag: ");
      http.append(createChecksum(dest));
      http.append("\r\n");
      
      http.append("Content-Length: ");
      if (length < 0)
      {
        http.append("0");
      }
      else
      {
        http.append(length);
      }
      http.append("\r\n");
      http.append("Connection: close\r\n");
      http.append("Content-Type: ");
      http.append(mimeType);
      http.append("\r\n\r\n");
      

      out.write(http.toString().getBytes());
      if ((length > 0) && (dest != null))
      {

        out.write(dest, 0, length);
      }
    }
    catch (Exception e) {}
  }
  



  private String createHTTPDateString()
  {
    String httpDateString = httpDateTime.format(new Date());
    return httpDateString + " GMT";
  }
  
  private void sendResponse(String responseMessage)
  {
    sendResponse(responseMessage, "text/html", true);
  }
  
  private void sendResponse(String responseMessage, String mime, boolean logConsol)
  {
    try
    {
      String response = createHttpResponse(responseMessage, mime + "; charset=UTF-8");
      if (logConsol)
      {
        System.out.println(responseMessage);
      }
      outStream.print(response);
      outStream.flush();
    }
    catch (Exception e)
    {
      System.out.println("Failed to send response: " + e.toString());
    }
    finally
    {
      if (outStream != null)
      {
        outStream.close();
        outStream = null;
      }
      if (socket != null)
      {
        try
        {
          socket.close();
        }
        catch (Exception e)
        {
          System.out.println("Failed to send response: " + e.toString());
        }
        socket = null;
      }
    }
  }
  
  private void sendMediaResponse(String mediaFile, String mimeType)
  {
    try
    {
      createHttpMediaResponse(mediaFile, mimeType, outStream);
      outStream.flush();
    }
    catch (Exception e)
    {
      System.out.println("Failed to send response: " + e.toString());
    }
    finally
    {
      if (outStream != null)
      {
        outStream.close();
        outStream = null;
      }
      if (socket != null)
      {
        try
        {
          socket.close();
        }
        catch (Exception e)
        {
          System.out.println("Failed to send file: " + e.toString());
        }
        socket = null;
      }
    }
  }
  
  private void sendMediaResponse(byte[] array, String mimeType)
  {
    try
    {
      createHttpMediaResponse(array, mimeType, outStream);
      outStream.flush();
    }
    catch (Exception e)
    {
      System.out.println("Failed to send response: " + e.toString());
    }
    finally
    {
      if (outStream != null)
      {
        outStream.close();
        outStream = null;
      }
      if (socket != null)
      {
        try
        {
          socket.close();
        }
        catch (Exception e)
        {
          System.out.println("Failed to send file: " + e.toString());
        }
        socket = null;
      }
    }
  }
  





  private int getMediaFile(String filename, byte[] dest)
  {
    try
    {
      FileInputStream in = new FileInputStream(filename);
      int length = in.read(dest);
      in.close();
      
      return length;
    }
    catch (Exception e) {}
    

    return -1;
  }
  






  private static long createChecksum(byte[] bytes)
  {
    long checksum = 0L;
    for (int i = 0; i < bytes.length; i++)
    {
      checksum += bytes[i];
    }
    return checksum;
  }
  
  private static boolean saveByteArray(String filePath, byte[] body)
  {
    try
    {
      FileOutputStream fos = new FileOutputStream(filePath);
      fos.write(body);
      fos.close();
      return true;
    }
    catch (Exception e) {}
    
    return false;
  }
  







  private void writeLine(String filename, String text, boolean append)
  {
    String logMessage = text + "\r\n";
    File file = new File(filename);
    try
    {
      FileOutputStream o = new FileOutputStream(file, append);
      o.write(logMessage.getBytes());
      o.close();
    }
    catch (Exception localException) {}
  }
  




  private long getFileLength(String filename)
  {
    File file = new File(filename);
    long length = (int)file.length();
    return length;
  }
  
  private String createReponseForm()
  {
    StringBuffer table = new StringBuffer();
    table.append("Paste or type json, xml, html or text response to learn and press Learn<br><br>");
    table.append("<form action=\"learn\" method=\"post\">");
    table.append("<textarea name='text' rows='30' cols='150'></textarea><br><br>");
    table.append("<input type=\"submit\" id='learn' value=\"Learn\">");
    table.append("</form>");
    return table.toString();
  }
  
  private String detectMime(String text)
  {
    if (isXml(text))
    {
      if (text.startsWith("<html>"))
      {
        return "text/html";
      }
      return "application/xml";
    }
    if (isJson(text))
    {
      return "application/json";
    }
    

    return "text/plain";
  }
  

  private boolean isXml(String text)
  {
    String XML_PATTERN_STR = "<(\\S+?)(.*?)>(.*?)</\\1>";
    Pattern pattern = Pattern.compile("<(\\S+?)(.*?)>(.*?)</\\1>", 42);
    Matcher matcher = pattern.matcher(text);
    return matcher.matches();
  }
  

  private boolean isJson(String text)
  {
    String XML_PATTERN_STR = "\\{(.*?)\\:(.*?)\\}";
    Pattern pattern = Pattern.compile("\\{(.*?)\\:(.*?)\\}", 42);
    Matcher matcher = pattern.matcher(text);
    return matcher.matches();
  }
  
  private Object loadObject(String filepath)
  {
    try
    {
      FileInputStream fileIn = new FileInputStream(filepath);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object object = in.readObject();
      in.close();
      fileIn.close();
      return object;
    }
    catch (Exception e) {}
    
    return null;
  }
  

  private boolean saveObject(String filepath, Object object)
  {
    try
    {
      FileOutputStream fileOut = new FileOutputStream(filepath);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(object);
      out.flush();
      out.close();
      fileOut.close();
      return true;
    }
    catch (Exception e) {}
    
    return false;
  }
}
