package javabookapp;

/**
 *
 * @author Iurii
 */
public class JavaBook {
    private String bookTitle;
    private String bookFeedback;
    private String bookAuthor;
    private String bookCategory;
    private String bookStatus;
    private String bookProgress;

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookFeedback() {
        return bookFeedback;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public String getBookProgress() {
        return bookProgress;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookFeedback(String bookFeedback) {
        this.bookFeedback = bookFeedback;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setBookProgress(String bookProgress) {
        this.bookProgress = bookProgress;
    }
    
}
