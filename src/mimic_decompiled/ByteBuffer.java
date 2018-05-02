package mimic;

import java.util.Vector;

public class ByteBuffer
{
  private static final int BYTES_IN_BLOCK = 500;
  private Vector<byte[]> blocks;
  private byte[] lastBlock;
  private int noBytesTotal = 0;
  private int noBytesLastBlock = 0;
  
  ByteBuffer()
  {
    blocks = new Vector();
    lastBlock = new byte['Ǵ'];
    blocks.add(lastBlock);
  }
  
  public void addByte(byte b)
  {
    if (noBytesLastBlock == 500)
    {

      noBytesLastBlock = 0;
      
      lastBlock = new byte['Ǵ'];
      blocks.add(lastBlock);
    }
    
    lastBlock[noBytesLastBlock] = b;
    
    noBytesTotal += 1;
    noBytesLastBlock += 1;
  }
  
  public byte[] getByteArray()
  {
    byte[] array = new byte[noBytesTotal];
    int noAdded = 0;
    
    for (int blockNo = 0; blockNo < blocks.size(); blockNo++)
    {
      byte[] block = (byte[])blocks.elementAt(blockNo);
      for (int i = 0; i < block.length; i++)
      {
        array[noAdded] = block[i];
        noAdded++;
        if (noAdded == noBytesTotal)
        {

          return array;
        }
      }
    }
    
    return array;
  }
}
