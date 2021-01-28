package covidproject;

import java.util.ArrayList;

public class DataVaccinations {
	private ArrayList<String> xs;
	private ArrayList<Integer> ys;

	public DataVaccinations() {
		xs = new ArrayList<String>();
		ys = new ArrayList<Integer>();
	}

	public ArrayList<String> getXValues() {
		return xs;
	}

	public ArrayList<Integer> getYValues() {
		return ys;
	}

	public void add(String x, int y) {
		xs.add(x);
		ys.add(y);
	}
}
