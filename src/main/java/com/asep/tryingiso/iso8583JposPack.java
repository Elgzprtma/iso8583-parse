package com.asep.tryingiso;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class iso8583JposPack {
    public static void main(String[] args) throws ISOException {

        InputStream is = iso8583JposPack.class.getResourceAsStream("/iso8583JPos.xml");
        GenericPackager packager = new GenericPackager(is);
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);


        isoMsg.set(0, "0100");


        isoMsg.set(3, "020000");


        isoMsg.set(4, "5000");


        isoMsg.set(7, new SimpleDateFormat("MMddHHmmss").format(new Date()));


        isoMsg.set(11, "123456");


        isoMsg.set(48, "Example Value");

        // pack the ISO 8583 Message
        byte[] bIsoMsg = isoMsg.pack();

        // output ISO 8583 Message String
        String isoMessage = "";
        for (int i = 0; i < bIsoMsg.length; i++) {
            isoMessage += (char) bIsoMsg[i];
        }
        System.out.println("Packed ISO8385 Message = '"+isoMessage+"'");
    }
}
