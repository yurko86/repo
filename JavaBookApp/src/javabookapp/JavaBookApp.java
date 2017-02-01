package javabookapp;

import javafx.scene.image.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 *
 * @author iurii
 */
public class JavaBookApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        // create preference object
        PreferenceIO preference = new PreferenceIO();
        
        // create book object
        JavaBook book = preference.getValuesFromFile();
        
        // read values from ini file to book object
        
        // create grid
        GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
        
        // put labels and textfields to grid
        
        //book title        
        TextField bookAuthorTxt = new TextField(book.getBookAuthor());
        grid.add(bookAuthorTxt, 0, 0,2,1);
        
        //book title
        TextField bookTitleTxt = new TextField(book.getBookTitle());
        grid.add(bookTitleTxt, 0, 1,2,1);
                
        //
        //bookCategory
        Label bookCategoryLbl = new Label("Book Category:");
        grid.add(bookCategoryLbl, 0, 2);
        TextField bookCategoryTxt = new TextField(book.getBookCategory());
        grid.add(bookCategoryTxt, 1, 2);
        
        //bookStatus
        Label bookStatusLbl = new Label("Book Status:");
        grid.add(bookStatusLbl, 0, 3);
        TextField bookStatusTxt = new TextField(book.getBookStatus());
        grid.add(bookStatusTxt, 1, 3);
        
        //bookProgress
        Label bookProgressLbl = new Label("Book Progress:");
        grid.add(bookProgressLbl, 0, 4);
        TextField bookProgressTxt = new TextField(book.getBookProgress());
        grid.add(bookProgressTxt,1,4);
        
        //feedback
        Label feedbackLbl = new Label("Feedback:");
        grid.add(feedbackLbl, 0, 5);
        TextArea bookFeedback = new TextArea(book.getBookFeedback());
        grid.add(bookFeedback, 1, 5);
        
        
        // try to load image
        try{
        System.out.println("--try img--");        
            Image image;
            image =  new Image("http://ecx.images-amazon.com/images/I/51F08bXsljL._SX258_BO1,204,203,200_.jpg");
      //  System.out.println( getClass().getResource("./resources/1.png").toString());

            ImageView imageView = new ImageView();
            imageView.setImage(image);        
            grid.add(imageView, 2, 0,1,7);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
        // add buton to grid
        Button saveBtn = new Button();
        saveBtn.setText("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                book.setBookAuthor(bookAuthorTxt.getText());
                book.setBookTitle(bookTitleTxt.getText());
                book.setBookCategory(bookCategoryTxt.getText());
                book.setBookStatus(bookStatusTxt.getText());
                book.setBookProgress(bookProgressTxt.getText());
                book.setBookFeedback(bookFeedback.getText());
        
                preference.saveBook(book);
             }
        });
        grid.add(saveBtn, 0, 6);
        
               
        // set up containers
        Scene scene = new Scene(grid, 650, 400);
	primaryStage.setScene(scene);
        primaryStage.setTitle("Book app");        
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
