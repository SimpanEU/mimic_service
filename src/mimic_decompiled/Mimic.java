package mimic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.imageio.ImageIO;


public class Mimic
  implements Runnable
{
  private ServerSocket serverSocket;
  private Thread serverThread;
  private Socket socket;
  private static SimpleDateFormat simpleLogDateTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
  
  public static int SERVER_PORT = 8080;
  public static String DATA_FILEPATH = "data";
  public static String BRAIN_FILENAME = "brain";
  public static String CHARTERS_FILEPATH = "data/charters.csv";
  public static String SCRIPTS_FILEPATH = "scripts";
  public static String IMAGES_FILEPATH = "images";
  public static String SETTINGS_FILEPATH = "settings";
  public static String LOGS_FILEPATH = "logs";
  public static String WIDGETS_FILEPATH = "widgets";
  
  public static final String WEBSERVICE_PROPERTIES_FILE = "settings/webservice.properties";
  public static final String PARAMETER_PROPERTIES_FILE = "settings/parameter.properties";
  public static final String LICENSE_PROPERTIES_FILE = "settings/license.properties";
  public static final String PRODUCT_PROPERTIES_FILE = "product.properties";
  public static String CHANGE_REPOSITORY_FILE = "logs/change_repository.csv";
  
  public static final String TRANSLATION_PROPERTIES_FILE = "settings/translation.properties";
  private static Properties translationProperties = new Properties();
  
  private static Properties webServiceProperties = new Properties();
  private Properties parameterProperties = new Properties();
  private Properties licenseProperties = new Properties();
  private static Properties productProperties = new Properties();
  













































  public Mimic()
  {
    try
    {
      serverSocket = new ServerSocket(SERVER_PORT);
      serverThread = new Thread(this);
      serverThread.start();
    }
    catch (Throwable e)
    {
      System.out.println("Failed to initiate server: " + e.toString());
      System.exit(0);
    }
  }
  
























  public synchronized boolean loadStateTree(String filepath)
  {
    return false;
  }
  






















  public synchronized boolean saveStateTree()
  {
    return false;
  }
  




  public void stopServer()
  {
    System.exit(0);
  }
  



  public static void main(String[] args)
  {
    if (args.length > 1)
    {

      String port = args[1];
      SERVER_PORT = Integer.parseInt(port);
    }
    new Mimic();
  }
  
  public void run()
  {
    System.out.println("Ready to receive requests on port " + SERVER_PORT);
    try
    {
      for (;;)
      {
        socket = serverSocket.accept();
        

        ServiceThread serviceThread = new ServiceThread(socket, this);
        serviceThread.start();
      }
    }
    catch (SocketException e) {}catch (Throwable localThrowable) {}
  }
  







  public static Properties getWebServiceProperties()
  {
    return webServiceProperties;
  }
  
  public static String getWebServiceProperty(String key, String defaultValue)
  {
    return webServiceProperties.getProperty(key, defaultValue);
  }
  
  public static void setWebServiceProperty(String key, String value)
  {
    webServiceProperties.setProperty(key, value);
  }
  
  public static Properties getProductProperties()
  {
    return productProperties;
  }
  






  public static String getProductProperty(String key, String defaultValue)
  {
    String value = productProperties.getProperty(key, null);
    if (value != null)
    {
      return value;
    }
    return webServiceProperties.getProperty(key, defaultValue);
  }
  
  public static void setProductProperty(String key, String value)
  {
    productProperties.setProperty(key, value);
  }
  

  private void loadProperties()
  {
    try
    {
      webServiceProperties = new Properties();
      FileInputStream in = new FileInputStream("settings/webservice.properties");
      webServiceProperties.load(in);
      in.close();
    }
    catch (Exception localException) {}
    



    loadProductProperties(getProduct());
    

    try
    {
      parameterProperties = new Properties();
      FileInputStream in = new FileInputStream("settings/parameter.properties");
      parameterProperties.load(in);
      in.close();
    }
    catch (Exception localException1) {}
    



    try
    {
      licenseProperties = new Properties();
      FileInputStream in = new FileInputStream("settings/license.properties");
      licenseProperties.load(in);
      in.close();
    }
    catch (Exception localException2) {}
    



    try
    {
      FileInputStream in = new FileInputStream("settings/translation.properties");
      translationProperties.load(in);
      in.close();
    }
    catch (Exception localException3) {}
  }
  





  public void saveProperties()
  {
    try
    {
      FileOutputStream out = new FileOutputStream("settings/webservice.properties");
      if (out != null)
      {
        webServiceProperties.store(out, null);
        out.close();
      }
      out = new FileOutputStream("settings/license.properties");
      if (out != null)
      {
        licenseProperties.store(out, null);
        out.close();
      }
    }
    catch (Exception localException) {}
    



    saveProductProperties(getProduct());
  }
  
  private void delay(long milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    }
    catch (Exception localException) {}
  }
  


  public Properties getParameterProperties()
  {
    return parameterProperties;
  }
  
  public Properties getLicenseProperties()
  {
    return licenseProperties;
  }
  






  public String getProduct()
  {
    return webServiceProperties.getProperty("product", "");
  }
  






  public boolean setProduct(String product)
  {
    saveStateTree();
    

    if (!loadStateTree(product))
    {
      return false;
    }
    

    webServiceProperties.setProperty("product", product);
    

    loadProductProperties(product);
    
    return true;
  }
  
  public String getProductVersion()
  {
    return productProperties.getProperty("product_version", "1");
  }
  
  public void setProductVersion(String productVersion)
  {
    productProperties.setProperty("product_version", productVersion);
  }
  
  public static String getProductView()
  {
    return productProperties.getProperty("product_view", "desktop");
  }
  
  public static void setProductView(String productView)
  {
    productProperties.setProperty("product_view", productView);
  }
  
  public String getProductFrame()
  {
    return productProperties.getProperty("product_frame", "");
  }
  
  public void setProductFrame(String productFrame)
  {
    productProperties.setProperty("product_frame", productFrame);
  }
  
  public String getHomeLocator()
  {
    return productProperties.getProperty("home_locator", "");
  }
  
  public void setHomeLocator(String homeLocator)
  {
    productProperties.setProperty("home_locator", homeLocator);
  }
  
  public static String getTesterName()
  {
    return productProperties.getProperty("tester_name", "");
  }
  
  public static void setTesterName(String testerName)
  {
    productProperties.setProperty("tester_name", testerName);
  }
  





  public static String translateCommand(String command)
  {
    return translationProperties.getProperty(command, command);
  }
  

  public static String stringToHtml(String s)
  {
    String noLineFeeds = convertLinefeedsToHtmlBreaks(s);
    return link2Tag(noLineFeeds);
  }
  





  private static String convertLinefeedsToHtmlBreaks(String s)
  {
    StringBuffer buf = new StringBuffer();
    
    for (int i = 0; i < s.length(); i++)
    {
      int c = s.charAt(i);
      switch (c)
      {
      case 13: 
        break;
      case 10: 
        buf.append("<br>");
        break;
      case 11: case 12: default: 
        buf.append((char)c);
      }
      
    }
    return buf.toString();
  }
  





  private static String link2Tag(String s)
  {
    int currentIndex = 0;
    
    for (;;)
    {
      if (currentIndex >= s.length())
      {
        return s;
      }
      
      int index = s.indexOf("http:", currentIndex);
      if (index == -1)
      {
        index = s.indexOf("www.", currentIndex);
      }
      currentIndex = index + 4;
      
      if (index == -1)
      {

        return s;
      }
      

      boolean addAnchor = true;
      if (index > 8)
      {
        String anchor = s.substring(index - 9, index);
        if ("<a href='".equals(anchor))
        {

          addAnchor = false;
        }
      }
      
      if (addAnchor)
      {
        int endIndex = s.length();
        int endIndexSpace = s.indexOf(' ', index);
        if (endIndexSpace != -1)
        {
          endIndex = endIndexSpace;
        }
        int endIndexNewLine = s.indexOf("<br>", index);
        if ((endIndexNewLine != -1) && (endIndexNewLine < endIndex))
        {
          endIndex = endIndexNewLine;
        }
        String link = s.substring(index, endIndex);
        
        StringBuffer buf = new StringBuffer();
        buf.append(s.substring(0, index));
        String alink = "<a href='" + addProtocol(link) + "'>" + link + "</a>";
        buf.append(alink);
        buf.append(s.substring(endIndex));
        s = buf.toString();
        
        currentIndex = index + alink.length();
      }
    }
  }
  
  private static String addProtocol(String text)
  {
    if (text.startsWith("www."))
    {

      return "http://" + text;
    }
    

    return text;
  }
  




  private void loadCustomLibraries(File folder)
  {
    try
    {
      File[] listOfFiles = folder.listFiles();
      
      for (int i = 0; i < listOfFiles.length; i++)
      {
        if (listOfFiles[i].isFile())
        {
          String filename = listOfFiles[i].getName();
          if (filename.toLowerCase().endsWith(".jar"))
          {
            addFile(listOfFiles[i]);
          }
        }
      }
    }
    catch (Exception localException) {}
  }
  

  private static void addFile(File f)
    throws IOException
  {
    addURL(f.toURI().toURL());
  }
  
  private static void addURL(URL u) throws IOException
  {
    URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
    Class<URLClassLoader> sysclass = URLClassLoader.class;
    
    try
    {
      Class[] parameters = { URL.class };
      Method method = sysclass.getDeclaredMethod("addURL", parameters);
      method.setAccessible(true);
      method.invoke(sysloader, new Object[] { u });
    }
    catch (Throwable t)
    {
      throw new IOException("Error, could not add URL to system classloader");
    }
  }
  





  public BufferedImage loadImage(String filename)
  {
    try
    {
      File file = new File(addRootFolder(filename));
      if (file.exists())
      {
        return ImageIO.read(file);
      }
      

      return null;
    }
    catch (Exception e) {}
    

    return null;
  }
  







  public String addRootFolder(String filepath)
  {
    if ((new File(filepath).isAbsolute()) || (parameterProperties == null))
    {

      return filepath;
    }
    String rootFolder = parameterProperties.getProperty("RootFolder");
    if (rootFolder == null)
    {
      return filepath;
    }
    File file = new File(rootFolder, filepath);
    return file.getPath();
  }
  
  private boolean loadProductProperties(String product)
  {
    try
    {
      productProperties = new Properties();
      String filepath = addRootFolder(DATA_FILEPATH + "/" + product + "/" + "product.properties");
      FileInputStream in = new FileInputStream(filepath);
      productProperties.load(in);
      in.close();
      return true;
    }
    catch (Exception e) {}
    
    return false;
  }
  

  private boolean saveProductProperties(String product)
  {
    try
    {
      String filepath = addRootFolder(DATA_FILEPATH + "/" + product + "/" + "product.properties");
      FileOutputStream out = new FileOutputStream(filepath);
      if (out != null)
      {
        productProperties.store(out, null);
        out.close();
      }
      
      return true;
    }
    catch (Exception e) {}
    
    return false;
  }
}
