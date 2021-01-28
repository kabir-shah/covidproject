package covidproject;

import java.util.ArrayList;

public class DataVaccinations {
	private ArrayList<Number> xs;
	private ArrayList<Number> ys;

	public DataVaccinations() {
		xs = new ArrayList<Number>();
		ys = new ArrayList<Number>();
	}

	public ArrayList<Number> getXValues() {
		return xs;
	}

	public ArrayList<Number> getYValues() {
		return ys;
	}

	public void add(Number x, Number y) {
		xs.add(x);
		ys.add(y);
	}
}
