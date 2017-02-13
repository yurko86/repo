//package javabookapp;

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
    private String bookImageUrl;
    private String bookRating;

    public JavaBook() {
        bookTitle = "Head First Java: A Brain-Friendly Guide";
        bookFeedback = "my feedback";
        bookAuthor = "Bert Bates, Kathy Sierra";
        bookCategory = "for beginers";
        bookStatus = "In progress";
        bookProgress = "1/700";
        bookImageUrl = "http://ecx.images-amazon.com/images/I/51F08bXsljL._SX258_BO1,204,203,200_.jpg";
        bookRating = "1";

    }

    public JavaBook(String bookTitle, String bookAuthor, String bookCategory, String bookProgress, String bookFeedback, String bookStatus, String bookImageUrl, String bookRating) {
        this.bookTitle = bookTitle;
        this.bookFeedback = bookFeedback;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookStatus = bookStatus;
        this.bookProgress = bookProgress;
        this.bookImageUrl = bookImageUrl;
        this.bookRating = bookRating;

    }

    public String getBookRating() {
        return bookRating;
    }

    public void setBookRating(String bookStarRating) {
        this.bookRating = bookStarRating;
    }

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

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
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
