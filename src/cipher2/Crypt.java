/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher2;

import java.util.Arrays;

/**
 *
 * @author Анатолий
 */
public abstract class Crypt {

    public abstract void setupIV(final byte[] iv);

    public abstract void setupKey(final byte[] key);

    public abstract byte[] crypt(final byte[] message);

    public String crypt(String incString, String key, String iv) {
        if (iv != null) {
            String ive="";
            for(int i=0;i<iv.length();i++){
                ive+=iv.substring(i, i+2)+" ";
                i++;
            }
            setupIV(convertIVData(ive));
        }
        String s1 = incString;
        setupKey(stringToByteKey(key));
        String hex = "";
        String result="";
        while (s1.length() >= 16) {
            char[] text = s1.substring(0, 16).toCharArray();
            hex = "";
            for (int i = 0; i < text.length; i++) {
                hex += String.format("%02x", (int) text[i]) + " ";
            }
            byte[] crypt = crypt(convertData(hex));
            hex = "";

            for (int i = 0; i < crypt.length; i++) {
                hex += Byte.toString(crypt[i]) + " ";
            }
            result+=hex;
            s1=s1.substring(16);
        }
        if (s1.length()!=0){
            char[] text = s1.toCharArray();
            hex="";
            for (int i = 0; i < text.length; i++) {
                hex += String.format("%02x", (int) text[i]) + " ";
            }
            byte[] crypt = crypt(convertData(hex));
            hex = "";

            for (int i = 0; i < crypt.length; i++) {
                hex += Byte.toString(crypt[i]) + " ";
            }
            result+=hex;
        }

        return result;
    }

    public String decrypt(String incString, String key, String iv) {
            if (iv != null) {
            String ive="";
            for(int i=0;i<iv.length();i++){
                ive+=iv.substring(i, i+2)+" ";
                i++;
            }
            setupIV(convertIVData(ive));
        }
        
        setupKey(stringToByteKey(key));
        String hex = "";
        String result="";
        int index=0;
        byte [] dec=convertDataDec(incString);
        for (int i=0;i<dec.length/16;i++) {
            byte[] text = Arrays.copyOfRange(dec, i*16,(i+1)*16);
            byte[] crypt = crypt(text);
            hex = "";

            for (int j = 0; j < crypt.length; j++) {
                hex +=""+ (char) crypt[j];
            }
            result+=hex;
            index+=16;
        }
        if (index<dec.length){
            byte[] text = Arrays.copyOfRange(dec, index,dec.length);
            byte[] crypt = crypt(text);
            hex = "";

            for (int i = 0; i < crypt.length; i++) {
                hex +=""+ (char) crypt[i];
            }
            result+=hex;
        }

        return result;
    }

    private byte[] stringToByteKey(String data) {
        String data1 = data;
        byte[] array = new byte[data1.length() / 2];
        int i = 0;
        while (data1.length() != 0) {
            array[i++] = (byte) (Integer.parseInt(data1.substring(0, 2), 16) & 0xFF);
            data1 = data1.substring(2);
        }
        return array;
    }


    private byte[] convertData(String... data) {
        byte[] array = new byte[data.length * 16];
        int i = 0;
        for (String tdata : data) {
            for (String value : tdata.split(" ")) {
                array[i++] = (byte) (Integer.parseInt(value, 16));
            }
        }
        return array;
    }

    private byte[] convertIVData(String data) {
        byte[] array = new byte[8];
        int i = 0;
        for (String value : data.split(" ")) {
            array[i++] = (byte) (Integer.parseInt(value, 16) & 0xFF);
        }
        return array;
    }
    private byte[] convertDataDec(String data) {
        byte[] array = new byte[data.split(" ").length];
        int i = 0;
            for (String value : data.split(" ")) {
                array[i++] = (byte) (Integer.parseInt(value, 10));
            }
         return array;
    }

}
