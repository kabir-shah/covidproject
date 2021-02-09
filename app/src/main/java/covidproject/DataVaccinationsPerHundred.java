package covidproject;

import java.util.ArrayList;

public class DataVaccinationsPerHundred {
	private String location;
	private ArrayList<Number> xs;
	private ArrayList<Float> ys;

	/**
	 * Create a new instance of this data structure.
	 * @param {String} location
	 */
	public DataVaccinationsPerHundred(String l) {
		location = l;
		xs = new ArrayList<Number>();
		ys = new ArrayList<Float>();
	}

	/**
	 * Location getter.
	 * @return {String} location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * X Values getter.
	 * @return {ArrayList<Number>} x values
	 */
	public ArrayList<Number> getXValues() {
		return xs;
	}

	/**
	 * Y Values getter.
	 * @return {ArrayList<Float>} y values
	 */
	public ArrayList<Float> getYValues() {
		return ys;
	}

	/**
	 * Add a new entry into the data structure.
	 * @param {Number} x value
	 * @param {Integer} y value
	 */
	public void add(Number x, Float y) {
		xs.add(x);
		ys.add(y);
	}
}
