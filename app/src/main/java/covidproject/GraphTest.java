package covidproject;

//import useful java utilities like arraylists and dates, as well as the main javafx tools like the axises and charts
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

// GraphTest class which is responsible for making the methods to show different kinds of graphs
public class GraphTest {
    VBox box = new VBox(); //new Vbox in which the graphs are added
    public GraphTest() { 
    }

    public VBox getGraph() { //a getter for the box so it can be used in other classes
        return box;
    }
    
    public void clearBox() { 
        /* 
        Method responsible for clearing the box so overlaps don't occur. 
        Easier to use this as the box is used in other classes too.
        */
        box.getChildren().clear();
    }
    public void vaccinations(ArrayList<DataVaccinations> data) { 
        /*
        Method responsible for making the graph of total vaccinations in a country. 
        Takes the data of vaccinations and utilizes javafx methods to add the data to the series, 
        make a graph, and add it to the box which is then displayed in other classes
        */
        clearBox(); //box is cleared so graphs dont overlap
        float maxValue = data.get(0).getYValues().get(data.get(0).getYValues().size() - 1); //max value is set to the last value in the data of the first country indicated
        for (int k = 1; k < data.size(); k++) { // for loop goes through all the data points
            if (data.get(k).getYValues().get(data.get(k).getYValues().size() - 1) > maxValue) {
                maxValue = data.get(k).getYValues().get(data.get(k).getYValues().size() - 1); // max value is changed if any point is greater than what it was set to initially
            }
        }
        for (int j = 0; j < data.size(); j++) {
            LineChart linechart; //new empty linechart object created
            XYChart.Series series = new XYChart.Series(); //new series objects created
            NumberAxis xAxis = new NumberAxis(1609488000000L, new Date().getTime(), 86400000); //x axis set to numerical representations of dates
            xAxis.setLabel("Time");   
            xAxis.setTickLabelFormatter((StringConverter<Number>)(new DateCoordinate())); //the numbers before are converted into actual dates
            
            NumberAxis yAxis = new NumberAxis(0, maxValue * 11 / 10, maxValue / 10); //y axis goes from 0 to a point slightly higher than the max so the graph doesn't look cramped, and the increment is a tenth of that number.
            yAxis.setLabel("Vaccines");

            linechart = new LineChart(xAxis, yAxis); //new linechart created using xAxis and yAxis as the data
            linechart.setAnimated(false); //disables the animation of the graph changing, crucial so graphs dont overlap which creates a break
            linechart.setTitle("Vaccines dispensed in " + data.get(j).getLocation()); //title is changed based on the current location

            series.setName("Vaccines over time");

            series.getData().clear(); //all the data inside is cleared to prevent overlap from previous graphs

            for (int i = 0; i < data.get(j).getXValues().size(); i++) {
                series.getData().add(new XYChart.Data(data.get(j).getXValues().get(i), data.get(j).getYValues().get(i))); 
                //loop runs through all the data and adds in the x and y values to the series.
            }

            linechart.getData().clear();
            linechart.getData().add(series); //add the series to the linechart

            box.getChildren().add(linechart); //add the linechart to the main box
        } 
        XYChart.Series seriesBar = new XYChart.Series(); //create a new series outside the loop for the bar graph
        for (int k = 0; k < data.size(); k++) {
            seriesBar.getData().add(new XYChart.Data(data.get(k).getLocation(), data.get(k).getYValues().get(data.get(k).getYValues().size() - 1))); //identical to the inner loop, but instead of adding x values adds the locations as strings
        }
        CategoryAxis xAxisBar = new CategoryAxis(); //x axis for a bar graph is a category axis not a number axis like for a line chart.
        NumberAxis yAxisBar = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar); //make new bar chart object using the x and y axises. 
        barChart.setTitle("Comparison of total vaccinations between countries"); 
        barChart.getData().clear();
        barChart.getData().add(seriesBar); //add the series to the bar chart

        box.getChildren().add(barChart); //add the bar chart to the box (which the line chart is already in)
    }
    public void vaccinationsPerHundred(ArrayList<DataVaccinationsPerHundred> data) { //identical method to vaccinations method but for displaying vacinations per hundred people
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
    public void peopleVaccinated(ArrayList<DataPeopleVaccinated> data) { //identical method to vaccinations method but for displaying the number of people vaccinated (as some people get multiple vaccines)
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
    public void dailyVaccinationsPerM(ArrayList<DataDailyVaccinationsPerM> data) { //identical method to vaccinations method but for displaying vacinations per million people
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
    public void dailyVaccinations(ArrayList<DataDailyVaccinations> data) { //identical method to vaccinations method but for displaying daily vaccinations
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
