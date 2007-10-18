package org.jscsi.scsi.protocol.mode;

import java.nio.ByteBuffer;

public abstract class ProtocolSpecificPort extends ModePage
{
   private static final int PAGE_CODE = 0x18;
   
   private int PAGE_LENGTH;

   public ProtocolSpecificPort(int subPageCode, int pageLength)
   {
      super((byte)PAGE_CODE, subPageCode);
      PAGE_LENGTH = pageLength;
   }
   
   @Override
   abstract protected void decodeModeParameters(int dataLength, ByteBuffer input);

   @Override
   abstract protected void encodeModeParameters(ByteBuffer output);

   @Override
   protected int getPageLength()
   {
      return PAGE_LENGTH;
   }
}