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
public class Cipher2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainWindow mw=new MainWindow();
        mw.setVisible(true);
        String s1="Текст";
        byte [] b1=s1.getBytes();
        String s2=new String(b1);
      /*  Cipher2 example = new Cipher2();
        Rabbit ex = new Rabbit();
        example.test(ex);
        */
    }

    private byte[] convertData(String... data) {
        byte[] array = new byte[data.length * 16];
        int i = 0;
        for (String tdata : data) {
            for (String value : tdata.split(" ")) {
                array[i++] = (byte) (Integer.parseInt(value, 16) & 0xFF);
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

    private void test(Rabbit rabbit, byte[] key, byte[] iv, byte[] data) {
        System.out.println("<--->");
        rabbit.reset();
        System.out.println("key is"+ Arrays.toString(key));
        
        rabbit.setupKey(key);
        if (iv != null) {
            rabbit.setupIV(iv);
        }
        System.out.println("string is "+Arrays.toString(data));
        byte[] crypt = rabbit.crypt(data.clone());
        System.out.println("coded string: "+Arrays.toString(crypt));
        rabbit.reset();
        rabbit.setupKey(key);
        if (iv != null) {
            rabbit.setupIV(iv);
        }
        rabbit.crypt(crypt);
        System.out.println("res is" +Arrays.toString(crypt));
        /*if (Arrays.equals(data, crypt)) {
            System.out.println("SUCCESS");
        } else {
            System.out.println("FAILED");
        }*/
        System.out.println("<--->");
    }

    public void test(Rabbit rabbit) {
        /* Appendix A: Test Vectors */
        /* A.1. Testing without IV Setup */
        System.out.println("Appendix A: Test Vectors");
        System.out.println();
        System.out.println("A.1. Test edited");

        //TEST 1
       /* System.out.print("\tTEST 1... ");
        test(rabbit,
        convertData("00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"),
        null,
        convertData("68 65 6c 6c 6f 20 77 6f 72 6c 64 0d 0a"));
       */ 
        String s1=rabbit.crypt("test text",
        "912813292E3D36FE3BFC62F1DC51C3AC", null);
        
        byte[] decode={103, -120, 23, 116, 50, 1, -104, 63, -73, 120, 125, -127, -44, 63, -43, 5};
        System.out.println(rabbit.decrypt(s1, "912813292E3D36FE3BFC62F1DC51C3AC", null));
/*        System.out.print(" \n \tTest original ");
        test(rabbit,
                convertData("91 28 13 29 2E 3D 36 FE 3B FC 62 F1 DC 51 C3 AC"),
                null,
                convertData(
                        "68 65 6c 6c 6f 20 77 6f 72 6c 64"));
/*
        System.out.print("\tTEST 3... ");
        test(rabbit,
                convertData("83 95 74 15 87 E0 C7 33 E9 E9 AB 01 C0 9B 00 43"),
                null,
                convertData(
                        "0C B1 0D CD A0 41 CD AC 32 EB 5C FD 02 D0 60 9B",
                        "95 FC 9F CA 0F 17 01 5A 7B 70 92 11 4C FF 3E AD",
                        "96 49 E5 DE 8B FC 7F 3F 92 41 47 AD 3A 94 74 28"));

        System.out.println();
        System.out.println("A.2. Testing with IV Setup");

        System.out.print("\tTEST 1... ");
        test(rabbit,
                convertData("00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"),
                convertIVData("00 00 00 00 00 00 00 00"),
                convertData(
                        "C6 A7 27 5E F8 54 95 D8 7C CD 5D 37 67 05 B7 ED",
                        "5F 29 A6 AC 04 F5 EF D4 7B 8F 29 32 70 DC 4A 8D",
                        "2A DE 82 2B 29 DE 6C 1E E5 2B DB 8A 47 BF 8F 66"));

        System.out.print("\tTEST 2... ");
        test(rabbit,
                convertData("00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"),
                convertIVData("C3 73 F5 75 C1 26 7E 59"),
                convertData(
                        "1F CD 4E B9 58 00 12 E2 E0 DC CC 92 22 01 7D 6D",
                        "A7 5F 4E 10 D1 21 25 01 7B 24 99 FF ED 93 6F 2E",
                        "EB C1 12 C3 93 E7 38 39 23 56 BD D0 12 02 9B A7"));

        System.out.print("\tTEST 3... ");
        test(rabbit,
                convertData("00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00"),
                convertIVData("A6 EB 56 1A D2 F4 17 27"),
                convertData(
                        "44 5A D8 C8 05 85 8D BF 70 B6 AF 23 A1 51 10 4D",
                        "96 C8 F2 79 47 F4 2C 5B AE AE 67 C6 AC C3 5B 03",
                        "9F CB FC 89 5F A7 1C 17 31 3D F0 34 F0 15 51 CB"));
*/
    }

}
