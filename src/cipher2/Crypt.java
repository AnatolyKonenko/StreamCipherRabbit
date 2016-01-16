/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher2;

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
            char text[] = iv.toCharArray();
            String hex="";
            for (int i=0;i<text.length;i++)
                hex += String.format("%02x", (int) text[i]);
        
            setupIV(stringToByte(hex));
        }
        setupKey(stringToByteKey(key));
        char text[] = incString.toCharArray();
        String hex="";
        for (int i=0;i<text.length;i++)
            hex +=String.format("%02x", (int) text[i])+" ";
        byte[] crypt = crypt(convertData(hex));
        hex="";
        for (int i=0;i<crypt.length;i++){
            hex+=Byte.toString(crypt[i])+" ";
        }
        return hex;
    }

    public String decrypt(String incString, String key, String iv) {
        if (iv != null) {
            char text[] = iv.toCharArray();
            String hex="";
            for (int i=0;i<text.length;i++)
                hex += String.format("%02x", (int) text[i]);
        
            setupIV(stringToByte(hex));
        } 
        byte todec[]=convertDataDec(incString);
        setupKey(stringToByteKey(key));
        byte [] cript= crypt(todec);
        String res="";
        for (int i=0;i<cript.length;i++){
            res+=""+(char)cript[i];
        }
        return res;
    }
  
    private byte[] stringToByteKey(String data){
        String data1=data;
        byte[] array = new byte[data1.length()/2];
        int i = 0;
        while (data1.length()!=0){
            array[i++] = (byte) (Integer.parseInt(data1.substring(0, 2), 16) & 0xFF);
            data1=data1.substring(2);
        }
        return array;        
    }
     
    private byte[] stringToByte(String data) {
        String data1=data;
        byte[] array = new byte[data1.length()/16+15];
        int i = 0;
        while (data1.length()!=0){
            array[i++] = (byte) (Integer.parseInt(data1.substring(0, 2), 16) & 0xFF);
            data1=data1.substring(2);
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
    
    private byte[] convertDataDec(String... data) {
        byte[] array = new byte[data.length * 16];
        int i = 0;
        for (String tdata : data) {
            for (String value : tdata.split(" ")) {
                array[i++] = (byte) (Integer.parseInt(value, 10));
            }
        }
        return array;
    }
        
        
    

}
