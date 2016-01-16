/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Анатолий
 */
public class Fileworker {
    
    public static String loadTextFromFile(File file){
        String result="";
        try {
            Scanner read= new Scanner(file);
            while (read.hasNextLine()){
                result+=read.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fileworker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static String loadTextFromFile(String place){
        File file= new File(place);
        String result="";
        try {
            Scanner read= new Scanner(file);
            while (read.hasNextLine()){
                result+=read.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fileworker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static boolean saveTextToFile(File file, String text){
            try {
                try (FileWriter fw = new FileWriter(file)) {
                    fw.write(text);
                    fw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Fileworker.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            return true;
}
    
    
    public static boolean saveTextToFile(String place, String text){
        PrintWriter writer;
        try {
            writer = new PrintWriter(place, "UTF-8");
            writer.println(text);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Fileworker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
        return true;
    }
}
