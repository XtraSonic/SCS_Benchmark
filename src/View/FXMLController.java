/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.TestingUnits.FloatingPointTestingUnit;
import Model.TestingUnits.IntegerTestingUnit;
import Model.TestingUnits.PrimeNumberTestUnit;
import Model.TestingUnits.StringSortingTestingUnit;
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
        benchmarks.add(new Benchmark(new IntegerTestingUnit()));
        benchmarks.add(new Benchmark(new FloatingPointTestingUnit()));
        benchmarks.add(new Benchmark(new PrimeNumberTestUnit()));
        benchmarks.add(new Benchmark(new StringSortingTestingUnit()));

        setBenchmark(benchmarks.get(0), integerTestsProgressBar, integerIterationsProgressBar, runIntegerTestButton);
        setBenchmark(benchmarks.get(1), floatingPointTestsProgressBar, floatingPointIterationsProgressBar, runFloatingPointTestButton);
        setBenchmark(benchmarks.get(2), primeTestsProgressBar, primeIterationsProgressBar, runPrimeTestButton);
        setBenchmark(benchmarks.get(3), stringTestsProgressBar, stringIterationsProgressBar, runStringTestButton);
    }

    @Override
    public void update(Observable o, Object o1)
    {

        System.out.println("asgasdf");
    }

    private void setBenchmark(Benchmark b, ProgressBar integerTestsProgressBar, ProgressBar integerIterationsProgressBar, Button runIntegerTestButton)
    {
        MyProgressBar mpb = new MyProgressBar(integerTestsProgressBar, integerIterationsProgressBar);
        b.addObserver(mpb);
        runIntegerTestButton.setOnAction(e ->
        {
            Thread t = new Thread(b);
            t.start();
        });
    }

}
