package covidproject;

import java.util.ArrayList;

public class DataVaccinations {
	private ArrayList<Integer> xs;
	private ArrayList<Integer> ys;

	public DataVaccinations() {
		xs = new ArrayList<Integer>();
		ys = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getXValues() {
		return xs;
	}

	public ArrayList<Integer> getYValues() {
		return ys;
	}

	public void add(int x, int y) {
		xs.add(x);
		ys.add(y);
	}
}
