package com.asep.tryingiso;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import java.io.InputStream;

public class iso8583JPosUnpack {
    public static void main(String[] args) throws ISOException {
        InputStream is = iso8583JposPack.class.getResourceAsStream("/iso8583JPos.xml");
        GenericPackager packager = new GenericPackager(is);
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);

        // this is ISO8583 Message that we will Unpack
        String isoMessage = "080020200000008000000000000000013239313130303031";// first, we must convert ISO8583 Message String to byte[]
        byte[] bIsoMessage = new byte[isoMessage.length()];
        for (int i = 0; i < bIsoMessage.length; i++) {
            bIsoMessage[i] = (byte) (int) isoMessage.charAt(i);
        }

        // second, we unpack the message
        isoMsg.unpack(bIsoMessage);

        // last, print the unpacked ISO8583
        System.out.println("MTI='"+isoMsg.getMTI()+"'");
        for(int i=1; i<=isoMsg.getMaxField(); i++){
            if(isoMsg.hasField(i))
                System.out.println(i+"='"+isoMsg.getString(i)+"'");
        }
    }
}
