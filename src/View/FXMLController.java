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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class FXMLController implements Initializable, Observer {

    @FXML public Button runIntegerTestButton;
    @FXML public Button runFloatingPointTestButton;
    @FXML public Button runPrimeTestButton;
    @FXML public Button runStringTestButton;
    @FXML public Button runUserTestButton;
    @FXML public Button runAllTestButton;

    @FXML public LineChart<String, Integer> resultChart;

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
        series.getData().add(new XYChart.Data<>("Refference", 100));
        resultChart.getData().add(series);
        List<Benchmark> benchmarks = new ArrayList<>();
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
            for (Benchmark b : benchmarks)
            {
                Thread t = new Thread(b);
                t.start();
            }
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
        mc.addObserver(this);

        runTestButton.setOnAction(e ->
        {
            Thread t = new Thread(b);
            t.start();
            /*try
            {
                t.join();
                Map<String, Integer> results = b.getScore();
                results.entrySet().stream().forEach(entity ->
                {
                    XYChart.Data<String, Integer> d = new XYChart.Data<>(entity.getKey(), entity.getValue());
                    series.getData().add(d);
                    resultChart.getData().addAll(series);
                });

            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }*/

        });
    }

    @Override
    public void update(Observable o, Object o1)
    {

        Double result = 1.;
        result *= Long.parseLong(integerScore.getText());
        System.out.println("Update integerScore:" + integerScore.getText()); //+ ' ' + Long.parseLong(integerScore.getText()) + ' '+ result);
        result *= Long.parseLong(floatScore.getText());
        System.out.println("Update floatScore:" + floatScore.getText());
        result *= Long.parseLong(primeScore.getText());
        System.out.println("Update primeScore:" + primeScore.getText());
        result *= Long.parseLong(stringScore.getText());
        System.out.println("Update stringScore:" + stringScore.getText());
        result = Math.pow(result, 1. / 4);
        result = (double)Math.round(result);
        String stringResult = result.toString();
        System.out.println("Update multiplication rez:" + stringResult);
        Platform.runLater(() ->
        {
            overallScore.setText(stringResult);
        });
        System.out.println("Update overallScore:" + overallScore.getText());
    }

}
