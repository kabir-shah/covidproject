package covidproject;

import covidproject.DateCoordinate;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.chart.LineChart; 
import javafx.scene.chart.BarChart; 
import javafx.scene.chart.CategoryAxis; 
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
    
    public void clearBox() {
        box.getChildren().clear();
    }
    public void vaccinations(ArrayList<DataVaccinations> data) {
        clearBox();
        float maxValue = data.get(0).getYValues().get(data.get(0).getYValues().size() - 1);
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
        XYChart.Series seriesBar = new XYChart.Series();
        for (int k = 0; k < data.size(); k++) {
            seriesBar.getData().add(new XYChart.Data(data.get(k).getLocation(), data.get(k).getYValues().get(data.get(k).getYValues().size() - 1)));
        }
        CategoryAxis xAxisBar = new CategoryAxis();
        NumberAxis yAxisBar = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar); 
        barChart.setTitle("Comparison of total vaccinations between countries"); 
        barChart.getData().clear();
        barChart.getData().add(seriesBar);

        box.getChildren().add(barChart);
    }
    public void vaccinationsPerHundred(ArrayList<DataVaccinationsPerHundred> data) {
        clearBox();
        float maxValue = data.get(0).getYValues().get(data.get(0).getYValues().size() - 1);
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
            
            NumberAxis yAxis = new NumberAxis(0, 100, 10); 
            yAxis.setLabel("Vaccines per hundred people");

            linechart = new LineChart(xAxis, yAxis);
            linechart.setAnimated(false);
            linechart.setTitle("Vaccines dispensed per hundred people in " + data.get(j).getLocation());

            series.setName("Vaccines per hundred people over time");


            series.getData().clear();

            for (int i = 0; i < data.get(j).getXValues().size(); i++) {
                series.getData().add(new XYChart.Data(data.get(j).getXValues().get(i), data.get(j).getYValues().get(i)));
            }

            linechart.getData().clear();
            linechart.getData().add(series);

            
            box.getChildren().add(linechart);
        }
        XYChart.Series seriesBar = new XYChart.Series();
        for (int k = 0; k < data.size(); k++) {
            seriesBar.getData().add(new XYChart.Data(data.get(k).getLocation(), data.get(k).getYValues().get(data.get(k).getYValues().size() - 1)));
        }
        CategoryAxis xAxisBar = new CategoryAxis();
        NumberAxis yAxisBar = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar); 
        barChart.setTitle("Comparison of vaccinations per hundred between countries"); 
        barChart.getData().clear();
        barChart.getData().add(seriesBar);

        box.getChildren().add(barChart);
    }
    public void peopleVaccinated(ArrayList<DataPeopleVaccinated> data) {
        clearBox();
        float maxValue = data.get(0).getYValues().get(data.get(0).getYValues().size() - 1);
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
            yAxis.setLabel("People");

            linechart = new LineChart(xAxis, yAxis);
            linechart.setAnimated(false);
            linechart.setTitle("People who received the vaccine in " + data.get(j).getLocation());

            series.setName("People receiving the vaccine over time");


            series.getData().clear();

            for (int i = 0; i < data.get(j).getXValues().size(); i++) {
                series.getData().add(new XYChart.Data(data.get(j).getXValues().get(i), data.get(j).getYValues().get(i)));
            }

            linechart.getData().clear();
            linechart.getData().add(series);

            
            box.getChildren().add(linechart);
        }
        XYChart.Series seriesBar = new XYChart.Series();
        for (int k = 0; k < data.size(); k++) {
            seriesBar.getData().add(new XYChart.Data(data.get(k).getLocation(), data.get(k).getYValues().get(data.get(k).getYValues().size() - 1)));
        }
        CategoryAxis xAxisBar = new CategoryAxis();
        NumberAxis yAxisBar = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar); 
        barChart.setTitle("Comparison of people vaccinated between countries"); 
        barChart.getData().clear();
        barChart.getData().add(seriesBar);

        box.getChildren().add(barChart);
    }
    public void dailyVaccinationsPerM(ArrayList<DataDailyVaccinationsPerM> data) {
        clearBox();
        float maxValue = data.get(0).getYValues().get(data.get(0).getYValues().size() - 1);
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
            
            NumberAxis yAxis = new NumberAxis(0, 1000000, 100000); 
            yAxis.setLabel("Daily vaccinations per million");

            linechart = new LineChart(xAxis, yAxis);
            linechart.setAnimated(false);
            linechart.setTitle("Daily vaccinations per million in " + data.get(j).getLocation());

            series.setName("Daily vaccinations per million over time");


            series.getData().clear();

            for (int i = 0; i < data.get(j).getXValues().size(); i++) {
                series.getData().add(new XYChart.Data(data.get(j).getXValues().get(i), data.get(j).getYValues().get(i)));
            }

            linechart.getData().clear();
            linechart.getData().add(series);

            
            box.getChildren().add(linechart);
        }
        XYChart.Series seriesBar = new XYChart.Series();
        for (int k = 0; k < data.size(); k++) {
            seriesBar.getData().add(new XYChart.Data(data.get(k).getLocation(), data.get(k).getYValues().get(data.get(k).getYValues().size() - 1)));
        }
        CategoryAxis xAxisBar = new CategoryAxis();
        NumberAxis yAxisBar = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar); 
        barChart.setTitle("Comparison of daily vaccinations per million between countries"); 
        barChart.getData().clear();
        barChart.getData().add(seriesBar);

        box.getChildren().add(barChart);
    }
    public void dailyVaccinations(ArrayList<DataDailyVaccinations> data) {
        clearBox();
        float maxValue = data.get(0).getYValues().get(data.get(0).getYValues().size() - 1);
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
            yAxis.setLabel("Daily vaccinations");

            linechart = new LineChart(xAxis, yAxis);
            linechart.setAnimated(false);
            linechart.setTitle("Daily vaccinations in " + data.get(j).getLocation());

            series.setName("Daily vaccinations over time");


            series.getData().clear();

            for (int i = 0; i < data.get(j).getXValues().size(); i++) {
                series.getData().add(new XYChart.Data(data.get(j).getXValues().get(i), data.get(j).getYValues().get(i)));
            }

            linechart.getData().clear();
            linechart.getData().add(series);

            
            box.getChildren().add(linechart);
        }
        XYChart.Series seriesBar = new XYChart.Series();
        for (int k = 0; k < data.size(); k++) {
            seriesBar.getData().add(new XYChart.Data(data.get(k).getLocation(), data.get(k).getYValues().get(data.get(k).getYValues().size() - 1)));
        }
        CategoryAxis xAxisBar = new CategoryAxis();
        NumberAxis yAxisBar = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar); 
        barChart.setTitle("Comparison of daily vaccinations between countries"); 
        barChart.getData().clear();
        barChart.getData().add(seriesBar);

        box.getChildren().add(barChart);
    }
}
