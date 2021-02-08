package covidproject;

import java.util.ArrayList;

public class DataVaccinationsPerHundred {
	private String location;
	private ArrayList<Number> xs;
	private ArrayList<Float> ys;

	public DataVaccinationsPerHundred(String l) {
		location = l;
		xs = new ArrayList<Number>();
		ys = new ArrayList<Float>();
	}

	public String getLocation() {
		return location;
	}

	public ArrayList<Number> getXValues() {
		return xs;
	}

	public ArrayList<Float> getYValues() {
		return ys;
	}

	public void add(Number x, Float y) {
		xs.add(x);
		ys.add(y);
	}
}
