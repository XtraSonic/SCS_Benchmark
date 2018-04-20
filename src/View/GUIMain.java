/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author XtraSonic
 */
public class GUIMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        URL url = this.getClass().getResource("FXML.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Benchmark");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
