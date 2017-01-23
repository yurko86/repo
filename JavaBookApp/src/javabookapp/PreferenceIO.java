/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javabookapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Iurii
 */



public class PreferenceIO {
 
private static String bookName;
private static String feedback;
private static Properties props = new Properties();

private static final String path = "preference.ini";

public PreferenceIO(){
	getValuesFromFile();
	}

static void getValuesFromFile() {
    try {
        FileInputStream in = new FileInputStream(path);
        props.load(in);
        in.close();
    
        bookName = props.getProperty("BOOK_NAME");
        feedback = props.getProperty("FEEDBACK");
    }
    catch(IOException ex){
        System.out.println(ex.getMessage());
        createPreferenceFile();
    }
}

static void createPreferenceFile(){
    try {
            FileOutputStream out = new FileOutputStream(path);
            bookName = "java book name";
            feedback = "initial feedback";
            props.setProperty("BOOK_NAME", bookName);
            props.setProperty("FEEDBACK", feedback);
            props.setProperty("LINK_TO_IMG", "3");
            props.store(out, null);
            out.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
}

static void saveValuesToFile(){
    try {
        FileOutputStream out = new FileOutputStream(path);

        props.setProperty("FEEDBACK", feedback);
        props.store(out, null);
        out.close();
    }
    catch (IOException ex){
        System.out.println(ex.getMessage());
    }
}

public String getBookName(){
    return bookName;
}

public String getFeedback(){
    return feedback;
}

public void setFeedback(String pFeedback){
    feedback = pFeedback;
    saveValuesToFile();
}

}
