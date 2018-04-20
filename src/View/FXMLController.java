/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class FXMLController implements Initializable, Observer {

    @FXML public Button runIntegerTestButton;
    @FXML public Button runFloatingPointTestButton;
    @FXML public Button runPrimeTestButton;
    @FXML public Button runStringTestButton;
    @FXML public Button runUserTestButton;
    @FXML public Button runAllTestButton;
    
    @FXML public LineChart resultChart;
    
    @FXML public ProgressBar integerProgressBar;
    @FXML public ProgressBar floatingPointProgressBar;
    @FXML public ProgressBar primeProgressBar;
    @FXML public ProgressBar stringProgressBar;
    @FXML public ProgressBar userProgressBar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
    

    @Override
    public void update(Observable o, Object o1)
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO
    }
    
}
