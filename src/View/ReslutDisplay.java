/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import benchmark.Benchmark;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author XtraSonic
 */
class ReslutDisplay extends Observable implements Observer {

    private LineChart chart;
    private XYChart.Series series;
    private final Benchmark b;
    private final Label score;

    public ReslutDisplay(LineChart chart, XYChart.Series series, Benchmark b, Label score)
    {
        this.chart = chart;
        this.series = series;
        this.b = b;
        this.score = score;
    }

    @Override
    public void update(Observable o, Object o1)
    {
        List<Double> updates = (List<Double>) o1;
        //System.out.println("Test: "+ updates.get(0) + updates.get(1) + updates.get(2));
        if (updates.get(2) == 0.)
        {
            return;
        }
        Map<String, Integer> results = b.getScore();
        System.out.println("Chart updating");
        System.out.println("Chart: " + results.toString());
        results.entrySet().stream().forEach(entity ->
        {
            Platform.runLater(() ->
            {
                series.getData().add(new XYChart.Data<>(entity.getKey(), entity.getValue()));
            });
            //chart.getData().addAll(series);

        });
        Long s = b.getOverallScore();
        Platform.runLater(() ->
        {
            score.setText(s.toString());
        });

        setChanged();
         notifyObservers();
        
    }

}
