package converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author iurii
 */
public class Converter extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception  {
        
        
        
        GridPane grid = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(grid, 600, 400);
        
        primaryStage.setTitle("Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    

   
    public static void main(String[] args) {
        launch(args);
    }
    
}