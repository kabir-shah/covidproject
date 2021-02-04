package covidproject;

import covidproject.DateCoordinate;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.StringConverter;
import javafx.scene.layout.VBox;

public class GraphTest {
    LineChart linechart;
    VBox box = new VBox();
    XYChart.Series series = new XYChart.Series();
    public GraphTest() {
    }

    public VBox getGraph() {
        return box;
    }

    public void updateGraph(DataVaccinations data) {
        NumberAxis xAxis = new NumberAxis(1609488000000L, new Date().getTime(), 86400000);
        xAxis.setLabel("Time");
        xAxis.setTickLabelFormatter((StringConverter<Number>)(new DateCoordinate()));

        NumberAxis yAxis = new NumberAxis(0, 50000000, 1000000);
        yAxis.setLabel("Vaccines");

        linechart = new LineChart(xAxis, yAxis);
        linechart.setAnimated(false);

        series.setName("Vaccines over time");

        series.getData().clear();

        for (int i = 0; i < data.getXValues().size(); i++) {
            series.getData().add(new XYChart.Data(data.getXValues().get(i), data.getYValues().get(i)));
        }

        linechart.getData().clear();
        linechart.getData().add(series);

        box.getChildren().clear();
        box.getChildren().add(linechart);
    }
}
