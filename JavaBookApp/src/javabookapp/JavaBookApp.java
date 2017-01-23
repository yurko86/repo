/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javabookapp;

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
        
        // create grid
        GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(10);
	grid.setVgap(10);
        
        // put labels and textfields to grid
        Label bookNameLbl = new Label("Book name:");
        grid.add(bookNameLbl, 0, 0);
        TextField bookName = new TextField();
        grid.add(bookName, 1, 0);
        Label feedbackLbl = new Label("Feedback:");
        grid.add(feedbackLbl, 0, 1);
        TextArea feedback = new TextArea();
        grid.add(feedback, 1, 1);
        
        //Image img = new Image("1.jpg");
        try{
        Image image = new Image("https://upload.wikimedia.org/wikipedia/commons/a/a7/Frankenstein's_monster_(Boris_Karloff).jpg");
        //Image image = new Image("file:\\c:/1.png");
        ///System.out.println(getClass().getResource("1.png"));
        ImageView imageView = new ImageView();
        imageView.setImage(image);        
        grid.add(imageView, 2, 0,1,2);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        }
        catch(Exception e){
        System.out.println(e.getMessage());
        }
        
        
        // add buton to grid
        Button btn = new Button();
        btn.setText("Save");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                preference.setFeedback(feedback.getText());
             }
        });
        grid.add(btn, 1, 2);
        
        // read values from ini file
             
        bookName.setText(preference.getBookName());
        feedback.setText(preference.getFeedback());
       
        // set up containers
        Scene scene = new Scene(grid, 650, 300);
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
