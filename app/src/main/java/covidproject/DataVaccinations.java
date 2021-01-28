package covidproject;

import java.util.ArrayList;

public class DataVaccinations {
	private ArrayList<Long> xs;
	private ArrayList<Integer> ys;

	public DataVaccinations() {
		xs = new ArrayList<Long>();
		ys = new ArrayList<Integer>();
	}

	public ArrayList<Long> getXValues() {
		return xs;
	}

	public ArrayList<Integer> getYValues() {
		return ys;
	}

	public void add(long x, int y) {
		xs.add(x);
		ys.add(y);
	}
}
