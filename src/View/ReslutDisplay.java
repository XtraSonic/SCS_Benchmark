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
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

/**
 *
 * @author XtraSonic
 */
class ReslutDisplay implements Observer {

    private BarChart chart;
    private XYChart.Series series;
    private final Benchmark b;
    private final Label score;
    private Map<String, XYChart.Data<String, Long>> data;

    public ReslutDisplay(BarChart chart, XYChart.Series series, Benchmark b, Label score)
    {
        this.chart = chart;
        this.series = series;
        this.b = b;
        this.score = score;
        List<String> names = b.getNames();
        data = names.stream().collect(Collectors.toMap(name -> name,
                                                       name -> new XYChart.Data<>(name, 0l)));
        Platform.runLater(() ->
        {
            data.entrySet().stream().forEach(e ->
            {
               // displayLabelForData(e.getValue());
                series.getData().add(e.getValue());
            });

        });
    }

    private void displayLabelForData(XYChart.Data<String, Long> data)
    {
        final Node node = data.getNode();
        final Text dataText = new Text(data.getYValue() + "");
        node.parentProperty().addListener((ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) ->
        {
            Group parentGroup = (Group) parent;
            parentGroup.getChildren().add(dataText);
        });

        node.boundsInParentProperty().addListener((ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) ->
        {
            dataText.setLayoutX(
                    Math.round(
                            bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
                    )
            );
            dataText.setLayoutY(
                    Math.round(
                            bounds.getMinY() - dataText.prefHeight(-1) * 0.5
                    )
            );
        });
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
        Map<String, Long> results = b.getTestTimes();
        System.out.println("Chart updating");
        System.out.println("Chart: " + results.toString());
        results.entrySet().stream().forEach(entity ->
        {
            Platform.runLater(() ->
            {
                //series.getData().add(new XYChart.Data<>(entity.getKey(), entity.getValue()));
                data.get(entity.getKey()).setYValue(entity.getValue());
            });
            //chart.getData().addAll(series);

        });
        Long s = b.getOverallScore();
        Platform.runLater(() ->
        {
            score.setText(s.toString());
        });

    }

}
