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

public class GraphTest {
    LineChart linechart;
    XYChart.Series series = new XYChart.Series(); 

    public GraphTest(ArrayList xValues, ArrayList yValues) {
        NumberAxis xAxis = new NumberAxis(1609488000000L, new Date().getTime(), 86400000); 
        xAxis.setLabel("Time");   
        xAxis.setTickLabelFormatter((StringConverter<Number>)(new DateCoordinate()));

        NumberAxis yAxis = new NumberAxis(0, 50000000, 1000000); 
        yAxis.setLabel("Vaccines");

        linechart = new LineChart(xAxis, yAxis);
        linechart.setAnimated(false);

        series.setName("Vaccines over time");
    }
    public LineChart getGraph() {   
        return linechart;
    }

    public void updateGraph(ArrayList xValues, ArrayList yValues) {
        series.getData().clear();
        for (int i = 0; i < xValues.size(); i++) {
            series.getData().add(new XYChart.Data(xValues.get(i), yValues.get(i)));
        }
        linechart.getData().clear();
        linechart.getData().add(series);
    }
}

