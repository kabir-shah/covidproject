package covidproject;

import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.util.StringConverter;

public class DateCoordinate extends StringConverter<Number> {
	static private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Convert a timestamp to a string.
	 * @param {Number} date
	 * @return {String} data formatted as a string
	 */
	public String toString(Number date) {
		// We can assume the date fits in a long, format it with simple data format.
		return sdf.format(new Date(date.longValue())).toString();
	}

	/**
	 * Convert a date string to a timestamp.
	 * @param {String} date
	 * @return {Number} timestamp
	 */
	public Number fromString(String date) {
		// Attempt to parse the date and create a data object to get the timestamp.
		try {
			Long time = sdf.parse(date).getTime();
			return time;
		} catch (Exception e) {
			System.out.println("ERROR: Failed to parse date: " + date);
			return 0L;
		}
	}
}
