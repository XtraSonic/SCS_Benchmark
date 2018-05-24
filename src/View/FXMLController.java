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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author XtraSonic
 */
public class FXMLController implements Initializable {

    @FXML public Button runIntegerTestButton;
    @FXML public Button runFloatingPointTestButton;
    @FXML public Button runPrimeTestButton;
    @FXML public Button runStringTestButton;
    @FXML public Button runUserTestButton;
    @FXML public Button runAllTestButton;

    @FXML public BarChart<String, Integer> resultChart;

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

    @FXML private Label overallScore;
    @FXML private Label integerScore;
    @FXML private Label floatScore;
    @FXML private Label primeScore;
    @FXML private Label stringScore;

    private int seed = 37841;
    private XYChart.Series<String, Integer> series = new XYChart.Series<>();
    private boolean all = false;
    private int count = 0;
    List<Benchmark> benchmarks = new ArrayList<>();
    //private List<XYChart.Series> seriesList;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        //resultChart.setAnimated(true);
        resultChart.getData().add(series);
        resultChart.getYAxis().setLabel("Time (nanoseconds)");
        benchmarks.add(new Benchmark(new IntegerTestingUnit(seed)));
        benchmarks.add(new Benchmark(new FloatingPointTestingUnit(seed)));
        benchmarks.add(new Benchmark(new PrimeNumberTestUnit()));
        benchmarks.add(new Benchmark(new StringSortingTestingUnit(seed)));

        setBenchmark(benchmarks.get(0), integerTestsProgressBar,
                     integerIterationsProgressBar,
                     runIntegerTestButton,
                     integerScore);

        setBenchmark(benchmarks.get(1), floatingPointTestsProgressBar,
                     floatingPointIterationsProgressBar,
                     runFloatingPointTestButton,
                     floatScore);

        setBenchmark(benchmarks.get(2), primeTestsProgressBar,
                     primeIterationsProgressBar,
                     runPrimeTestButton,
                     primeScore);

        setBenchmark(benchmarks.get(3), stringTestsProgressBar,
                     stringIterationsProgressBar,
                     runStringTestButton,
                     stringScore);

        runAllTestButton.setOnAction(e ->
        {
            count = 0;
            all = true;
            Thread t = new Thread(benchmarks.get(count));
            t.start();
            /*
            for (Benchmark b : benchmarks)
            {
                Thread t = new Thread(b);
                t.start();
            }*/
        });
    }

    private void setBenchmark(Benchmark b,
                              ProgressBar testsProgressBar,
                              ProgressBar iterationsProgressBar,
                              Button runTestButton,
                              Label score)
    {
        MyProgressBar mpb = new MyProgressBar(testsProgressBar, iterationsProgressBar);
        b.addObserver(mpb);
        ReslutDisplay mc = new ReslutDisplay(resultChart, series, b, score);
        b.addObserver(mc);

        runTestButton.setOnAction(e ->
        {
            Thread t = new Thread(b);
            t.start();
        });
        
        score.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
        {
            
            updateOverallScore();
            checkContinuation();
        });
    }

    public void updateOverallScore()
    {
        Double result = 1.;
        result *= Long.parseLong(integerScore.getText());
        result *= Long.parseLong(floatScore.getText());
        result *= Long.parseLong(primeScore.getText());
        result *= Long.parseLong(stringScore.getText());
        result = Math.pow(result, 1. / 4);
        result = (double) Math.round(result);
        String stringResult = result.toString();
        Platform.runLater(() ->
        {
            overallScore.setText(stringResult);
        });
    }

    private void checkContinuation()
    {
        //System.out.println("C = "+all +" "+ count);
        if(!all)
            return;
        if(benchmarks.size() > ++count)
        {
            Thread t = new Thread(benchmarks.get(count));
            t.start();
        }
        else
        {
            all = false;
        }
        
    }
}
