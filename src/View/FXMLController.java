/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.TestingUnits.FloatingPointTestingUnit;
import Model.TestingUnits.IntegerTestingUnit;
import benchmark.Benchmark;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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

    @FXML public ProgressBar integerTestsProgressBar;
    @FXML public ProgressBar integerIterationsProgressBar;
    @FXML public ProgressBar floatingPointTestsProgressBar;
    @FXML public ProgressBar floatingPointIterationsProgressBar;
    @FXML public ProgressBar primeTestsProgressBar;
    @FXML public ProgressBar primeIterationsProgressBar;
    @FXML public ProgressBar stringTestsProgressBar;
    @FXML public ProgressBar stringIterationsProgressBar;
    @FXML public ProgressBar userTestsProgressBar;
    @FXML public ProgressBar userIterationsProgressBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<Benchmark> benchmarks = new ArrayList<>();
        
        Benchmark b = new Benchmark(new IntegerTestingUnit());
        MyProgressBar mpb = new MyProgressBar(integerTestsProgressBar, integerIterationsProgressBar);
        b.addObserver(mpb);
        runIntegerTestButton.setOnAction(e ->
        {
            Thread t=new Thread(b);
            t.start();
        });
        benchmarks.add(b);
        
        Benchmark b2 = new Benchmark(new FloatingPointTestingUnit());
        MyProgressBar mpb2 = new MyProgressBar(floatingPointTestsProgressBar,floatingPointIterationsProgressBar);
        b2.addObserver(mpb2);
        runFloatingPointTestButton.setOnAction(e2 ->
        {
            Thread t2=new Thread(b2);
            t2.start();
        });
        benchmarks.add(b2);
    }

    @Override
    public void update(Observable o, Object o1)
    {
        
        System.out.println("asgasdf");
    }

}
