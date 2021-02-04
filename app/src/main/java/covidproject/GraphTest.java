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
    VBox box = new VBox();
    public GraphTest() { 
    }

    public VBox getGraph() {
        return box;
    }

    public void updateGraph(ArrayList<DataVaccinations> data) {
        box.getChildren().clear();
        long maxValue = data.get(0).getYValues().get(data.get(0).getYValues().size() - 1);
        for (int k = 1; k < data.size(); k++) {
            if (data.get(k).getYValues().get(data.get(k).getYValues().size() - 1) > maxValue) {
                maxValue = data.get(k).getYValues().get(data.get(k).getYValues().size() - 1);
            }
        }
        for (int j = 0; j < data.size(); j++) {
            LineChart linechart;
            XYChart.Series series = new XYChart.Series();
            NumberAxis xAxis = new NumberAxis(1609488000000L, new Date().getTime(), 86400000); 
            xAxis.setLabel("Time");   
            xAxis.setTickLabelFormatter((StringConverter<Number>)(new DateCoordinate()));
            
            NumberAxis yAxis = new NumberAxis(0, maxValue * 11 / 10, maxValue / 10); 
            yAxis.setLabel("Vaccines");

            linechart = new LineChart(xAxis, yAxis);
            linechart.setAnimated(false);
            linechart.setTitle("Vaccines dispensed in " + data.get(j).getLocation());

            series.setName("Vaccines over time");


            series.getData().clear();

            for (int i = 0; i < data.get(j).getXValues().size(); i++) {
                series.getData().add(new XYChart.Data(data.get(j).getXValues().get(i), data.get(j).getYValues().get(i)));
            }

            linechart.getData().clear();
            linechart.getData().add(series);

            
            box.getChildren().add(linechart);
        }
    }
}
