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

    private static Properties props = new Properties();

    private static final String FILE_PATH = "preference.ini";

    private static final String TITLE = "TITLE";
    private static final String AUTHOR = "AUTHOR";
    private static final String CATEGORY = "CATEGORY";
    private static final String PROGRESS = "PROGRESS";
    private static final String FEEDBACK = "FEEDBACK";
    private static final String STATUS = "STATUS";
    private static final String IMG_URL = "IMG_URL";
    private static final String RATING = "RATING";

    private JavaBook book;

    public PreferenceIO() {
    }

    JavaBook getValuesFromFile() {
        try {

            FileInputStream in = new FileInputStream(FILE_PATH);

            props.load(in);
            in.close();

            book = new JavaBook(
                    props.getProperty(TITLE),
                    props.getProperty(AUTHOR),
                    props.getProperty(CATEGORY),
                    props.getProperty(PROGRESS),
                    props.getProperty(FEEDBACK),
                    props.getProperty(STATUS),
                    props.getProperty(IMG_URL),
                    props.getProperty(RATING)
            );

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            createPreferenceFile();
        }
        return book;
    }

    void createPreferenceFile() {
        try {
            System.out.println("---Create default book---");

            // set default values
            book = new JavaBook();

            // write book object to file 
            saveValuesToFile(book);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    static void saveValuesToFile(JavaBook book) {
        try (FileOutputStream out = new FileOutputStream(FILE_PATH)) {

            props.setProperty(TITLE, book.getBookTitle());
            props.setProperty(AUTHOR, book.getBookAuthor());
            props.setProperty(CATEGORY, book.getBookCategory());
            props.setProperty(PROGRESS, book.getBookProgress());
            props.setProperty(STATUS, book.getBookStatus());
            props.setProperty(FEEDBACK, book.getBookFeedback());
            props.setProperty(IMG_URL, book.getBookImageUrl());
            props.setProperty(RATING, String.valueOf(book.getBookRating()));
            props.store(out, null);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public JavaBook getBook() {
        return book;
    }

    public void saveBook(JavaBook book) {
        saveValuesToFile(book);
    }
}
