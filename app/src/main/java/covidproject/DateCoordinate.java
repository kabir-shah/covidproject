package covidproject;

import java.util.Date;
import java.text.SimpleDateFormat;

import javafx.util.StringConverter;

public class DateCoordinate extends StringConverter<Number> {
	static private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String toString(Number date) {
		return sdf.format(new Date(date.longValue())).toString();
	}

	public Number fromString(String date) {
		try {
			Long time = sdf.parse(date).getTime();
			return time;
		} catch (Exception e) {
			System.out.println("ERROR: Failed to parse date: " + date);
			return 0L;
		}
	}
}
