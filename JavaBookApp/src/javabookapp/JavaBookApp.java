package javabookapp;

import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author iurii
 */
public class JavaBookApp extends Application {

    // map for labels
    private Map<Label, TextField> bookUI = new HashMap<>();

    private JavaBook book;

    @Override
    public void start(Stage primaryStage) {
        PreferenceIO preference = new PreferenceIO();
        book = preference.getValuesFromFile();
        GridPane grid = createGrid();        
        TextField bookAuthorTxt = new TextField(book.getBookAuthor());
        TextField bookTitleTxt = new TextField(book.getBookTitle());
        TextField bookCategoryTxt = new TextField(book.getBookCategory());
        TextField bookStatusTxt = new TextField(book.getBookStatus());
        TextField bookProgressTxt = new TextField(book.getBookProgress());
        
        //put labels to  map
        bookUI.put(new Label("Author:"), bookAuthorTxt);
        bookUI.put(new Label("Title:"), bookTitleTxt);
        bookUI.put(new Label("Category:"), bookCategoryTxt);
        bookUI.put(new Label("Status:"), bookStatusTxt);
        bookUI.put(new Label("Progress:"), bookProgressTxt);

        // put labels and textfields to grid        
        setLabelsOnGrid(grid);
        
        TextArea bookFeedback = new TextArea(book.getBookFeedback());
        //put feedback textfield
        setFeedbackOnGrid(grid,bookFeedback);
        
        ToggleGroup group = new ToggleGroup();
        //add radio button
        setRadiobuttonOnGrid(grid, group);

                
        // add buton to grid
        Button saveBtn = new Button("Save");

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                book.setBookAuthor(bookAuthorTxt.getText());
                book.setBookTitle(bookTitleTxt.getText());
                book.setBookCategory(bookCategoryTxt.getText());
                book.setBookStatus(bookStatusTxt.getText());
                book.setBookProgress(bookProgressTxt.getText());
                book.setBookFeedback(bookFeedback.getText());
                
                try {
                    book.setBookRating(group.getSelectedToggle().getUserData().toString());
                } catch (Exception e) {
                    System.err.println("Can't read radio button value");
                }
                preference.saveBook(book);
            }
        });
        grid.add(saveBtn, 2, 7);

        // try to load image
        
        ImageView imageView = new ImageView();
        grid.add(imageView, 2, 0, 1, 7);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        
        try {
            imageView.setImage(new Image(book.getBookImageUrl())); 

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // set up containers
        Scene scene = new Scene(grid, 650, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book app");
        primaryStage.show();

    }

    private GridPane createGrid() {
        // create grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setLabelsOnGrid(GridPane grid) {
        int rowPos = 0;
        for (Map.Entry<Label, TextField> entry : bookUI.entrySet()) {
            Label key = entry.getKey();
            TextField value = entry.getValue();
            grid.add(key, 0, rowPos);
            grid.add(value, 1, rowPos);
            rowPos++;
        }
    }

    private void setFeedbackOnGrid(GridPane grid, TextArea feedback){
            TextArea bookFeedback = feedback;
            grid.add(new Label("Feedback:"), 0, 5);
            grid.add(bookFeedback, 1, 5);
            
    }
    
    private void setRadiobuttonOnGrid(GridPane grid, ToggleGroup group) {
        grid.add(new Label("Rating:"), 0, 6);

        ArrayList<RadioButton> radioButtonGroup = new ArrayList<>();
        radioButtonGroup.add(new RadioButton("awful"));
        radioButtonGroup.add(new RadioButton("inadequate"));
        radioButtonGroup.add(new RadioButton("adequate"));
        radioButtonGroup.add(new RadioButton("good"));
        radioButtonGroup.add(new RadioButton("very good"));
        radioButtonGroup.add(new RadioButton("terrific"));

        HBox box = new HBox(20);

        for (RadioButton rb : radioButtonGroup) {
            rb.setToggleGroup(group);
            box.getChildren().add(rb);
            rb.setUserData(rb.getText());
            if (rb.getText().compareTo(book.getBookRating()) == 0) {
                rb.setSelected(true);
            }
        }

        grid.add(box, 1, 6, 2, 1);

    }

}
