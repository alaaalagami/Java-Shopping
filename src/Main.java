import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files


public class Main extends Application {

    // Entry point of the program, starts the GUI
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    // Gets called as the GUI starts
    public void start(Stage primaryStage) throws Exception {
        // load and start the GUI from the fxml file.
        // All other functionality originates from Controller.java
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/login.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();

        controller.setStage(primaryStage);
        primaryStage.setTitle("Online Shopping App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}