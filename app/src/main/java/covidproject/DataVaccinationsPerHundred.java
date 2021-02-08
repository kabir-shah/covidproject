package covidproject;

import java.util.ArrayList;

public class DataVaccinationsPerHundred {
	private String location;
	private ArrayList<Number> xs;
	private ArrayList<Integer> ys;

	public DataVaccinationsPerHundred(String l) {
		location = l;
		xs = new ArrayList<Number>();
		ys = new ArrayList<Integer>();
	}

	public String getLocation() {
		return location;
	}

	public ArrayList<Number> getXValues() {
		return xs;
	}

	public ArrayList<Integer> getYValues() {
		return ys;
	}

	public void add(Number x, Integer y) {
		xs.add(x);
		ys.add(y);
	}
}
