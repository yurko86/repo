package javabookapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Iurii
 */



public class PreferenceIO {
 
//private static String feedback;
private static Properties props = new Properties();

private static final String FILE_PATH = "preference.ini";

private static final String TITLE = "TITLE";
private static final String AUTHOR = "AUTHOR";
private static final String CATEGORY = "CATEGORY";
private static final String PROGRESS= "PROGRESS";
private static final String FEEDBACK= "FEEDBACK";
private static final String STATUS= "STATUS";


private final JavaBook book = new JavaBook();

public PreferenceIO(){
	//getValuesFromFile();
}

JavaBook getValuesFromFile() {
    try {
        
        FileInputStream in = new FileInputStream(FILE_PATH);
        props.load(in);
        in.close();
    
        book.setBookTitle( props.getProperty(TITLE));
        book.setBookAuthor(props.getProperty(AUTHOR));
        book.setBookCategory(props.getProperty(CATEGORY));
        book.setBookProgress(props.getProperty(PROGRESS));
        book.setBookFeedback(props.getProperty(FEEDBACK));
        book.setBookStatus(props.getProperty(STATUS));
        
    }
    catch(IOException ex){
        System.out.println(ex.getMessage());
        createPreferenceFile();
    }
    return book;
}

void createPreferenceFile(){
    try {
            System.out.println("---Create default book---");

            // set default values
            book.setBookTitle(TITLE);
            book.setBookAuthor(AUTHOR);
            book.setBookCategory(CATEGORY);
            book.setBookProgress(PROGRESS);
            book.setBookFeedback(FEEDBACK);
            book.setBookStatus(STATUS);
            
            // write book object to file 
            saveValuesToFile(book);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
}

static void saveValuesToFile(JavaBook book){
    try {
        FileOutputStream out = new FileOutputStream(FILE_PATH);

        props.setProperty(TITLE, book.getBookTitle());
        props.setProperty(AUTHOR, book.getBookAuthor());
        props.setProperty(CATEGORY,book.getBookCategory());
        props.setProperty(PROGRESS, book.getBookProgress());
        props.setProperty(STATUS, book.getBookStatus());
        props.setProperty(FEEDBACK, book.getBookFeedback());

        props.store(out, null);
        out.close();
    }
    catch (IOException ex){
        System.out.println(ex.getMessage());
    }
}

public JavaBook getBook(){
    return book;
}


public void saveBook(JavaBook book){    
    saveValuesToFile(book);
}

}
