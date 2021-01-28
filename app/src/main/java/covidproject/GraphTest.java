package covidproject;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.chart.LineChart; 
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.XYChart;

public class GraphTest {
    int[] time;
    int[] cases;
    public GraphTest(int[] xValues, int[] yValues) {
        time = xValues;
        cases = yValues;
    }
    public Group makeGraph() {   
        NumberAxis xAxis = new NumberAxis(0, 10, 1); 
        xAxis.setLabel("Time");   

        NumberAxis yAxis = new NumberAxis(0, 1000, 100); 
        yAxis.setLabel("Cases");

        LineChart linechart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series(); 
        series.setName("Cases over time"); 

        for (int i = 0; i < time.length; i++) {
            series.getData().add(new XYChart.Data(time[i], cases[i]));
        }

        linechart.getData().add(series);

        Group group1 = new Group(linechart);

        return group1;
    }
}

