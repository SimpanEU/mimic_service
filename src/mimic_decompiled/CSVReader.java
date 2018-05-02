package mimic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Vector;




public class CSVReader
{
  private BufferedReader bufReader = null;
  
  private List<String> headerLine = null;
  
  private List<String> nextLine = null;
  
  private char columnSeparator = '\t';
  







  public CSVReader(String filename)
    throws IOException
  {
    this(new File(filename));
  }
  







  public CSVReader(File file)
    throws IOException
  {
    this(new InputStreamReader(new FileInputStream(file), "UTF8"));
  }
  







  public CSVReader(Reader in)
    throws IOException
  {
    bufReader = new BufferedReader(in);
  }
  








  private char findColumnSeparator(String header)
  {
    if (header.indexOf(',') >= 0)
    {
      return ',';
    }
    if (header.indexOf(';') >= 0)
    {
      return ';';
    }
    if (header.indexOf('\t') >= 0)
    {
      return '\t';
    }
    if (header.indexOf('|') >= 0)
    {
      return '|';
    }
    if (header.indexOf(':') >= 0)
    {
      return ':';
    }
    return '\t';
  }
  





  public boolean hasNext()
  {
    return nextLine != null;
  }
  






  public Properties next()
    throws NoSuchElementException
  {
    if (nextLine == null)
    {
      throw new NoSuchElementException("Read past end of file");
    }
    if (nextLine.size() != headerLine.size())
    {

      throw new NoSuchElementException("Not the same no of columns as the header");
    }
    
    Properties p = new Properties();
    for (int i = 0; i < nextLine.size(); i++)
    {
      p.put(headerLine.get(i), nextLine.get(i));
    }
    

    try
    {
      nextLine = readNextLine();
    }
    catch (IOException e)
    {
      nextLine = null;
    }
    return p;
  }
  





  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Not supported");
  }
  






  public List<String> readNextLine()
    throws IOException
  {
    return readNextLine(false);
  }
  
  public List<String> readNextLine(boolean first) throws IOException
  {
    String nextCsvLine = readOneCsvLine(bufReader);
    if (nextCsvLine == null)
    {

      bufReader.close();
      return null;
    }
    if (first)
    {

      columnSeparator = findColumnSeparator(nextCsvLine);
    }
    Vector<String> valuesToImport = splitString(nextCsvLine, columnSeparator, '"', true, false);
    return valuesToImport;
  }
  






  private String readOneCsvLine(BufferedReader bufReader)
    throws IOException
  {
    String nextCsvLine = readCsvLine(bufReader);
    if (nextCsvLine == null)
    {
      return null;
    }
    if ("".equals(nextCsvLine.trim()))
    {

      return readOneCsvLine(bufReader);
    }
    return nextCsvLine;
  }
  







  private String readCsvLine(BufferedReader bufReader)
    throws IOException
  {
    StringBuffer buf = new StringBuffer();
    do
    {
      String thisLine = bufReader.readLine();
      if (thisLine == null)
      {
        if (buf.length() == 0)
        {
          return null;
        }
        

        return buf.toString();
      }
      



      if (buf.length() > 0)
      {
        buf.append("\n");
      }
      buf.append(thisLine);
    } while (countChars(buf.toString(), '"') % 2 != 0);
    

    return buf.toString();
  }
  


















  private Vector<String> splitString(String s, char delimiter, char protectDelimiter, boolean addEmpty, boolean addProtection)
  {
    Vector<String> v = new Vector();
    StringBuffer b = new StringBuffer();
    int isProtected = 0;
    int isSubProtected = 0;
    char lastChar = ' ';
    
    for (int i = 0; i < s.length(); i++)
    {
      char nextChar = s.charAt(i);
      if ((isProtected == 0) && (isSubProtected == 0) && (nextChar == delimiter))
      {

        if (b.length() == 0)
        {

          if (addEmpty)
          {

            v.add(b.toString().trim());
            b = new StringBuffer();
          }
          
        }
        else
        {
          v.add(b.toString().trim());
          b = new StringBuffer();
        }
      }
      else if ((nextChar == protectDelimiter) && (isSubProtected == 0))
      {

        if (isProtected == 0)
        {
          isProtected = 1;
        }
        else
        {
          isProtected = 0;
        }
        if (addProtection)
        {
          b.append(nextChar);
        }
        else if (lastChar == nextChar)
        {

          b.append(nextChar);
          
          nextChar = ' ';
        }
      }
      else
      {
        b.append(nextChar);
      }
      
      lastChar = nextChar;
    }
    

    if (b.length() == 0)
    {

      if (addEmpty)
      {

        v.add(b.toString().trim());
      }
      

    }
    else {
      v.add(b.toString().trim());
    }
    
    return v;
  }
  









  private int countChars(String str, char c)
  {
    int count = 0;
    for (int i = 0; i < str.length(); i++)
    {
      if (str.charAt(i) == c)
      {
        count++;
      }
    }
    return count;
  }
  





  public char getColumnSeparator()
  {
    return columnSeparator;
  }
  
  public List<String> getHeaderLine()
  {
    return headerLine;
  }
}
