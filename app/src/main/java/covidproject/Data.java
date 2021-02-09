package covidproject;

import java.io.InputStream;
import java.io.BufferedReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import covidproject.DataVaccinations;
import covidproject.DataVaccinationsPerHundred;
import covidproject.DataPeopleVaccinated;
import covidproject.DataDailyVaccinations;
import covidproject.DataDailyVaccinationsPerM;
import covidproject.DateCoordinate;

public class Data {
	static private DateCoordinate converter = new DateCoordinate();

	/**
	 * Load a path from a constant data directory.
	 * @param {String} file
	 * @return {Path} full file path
	 */
	static private Path load(String file) {
		return Paths.get("../data/", file);
	}

	/**
	 * Download the contents of a URL into a given file.
	 * @param {String} url
	 * @param {String} file
	 */
	static private void download(String url, String file) {
		// Create a generic download helper that downloads a file from a URL.
		try (InputStream input = URI.create(url).toURL().openStream()) {
			Files.copy(input, load(file), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println("ERROR: Failed to download data.");
			System.out.println(e);
		}
	}

	/**
	 * Update all datasets by redownloading their files.
	 */
	static public void update() {
		// Download the latest vaccination data. This is the only dataset that we were able to get the time to use.
		download("https://github.com/owid/covid-19-data/raw/master/public/data/vaccinations/vaccinations.csv", "vaccinations.csv");
	}

	/**
	 * Parse vaccination data from the CSV and add it to the data structure.
	 * @param {String} location
	 * @return {DataVaccinations} vaccination data
	 */
	static public DataVaccinations vaccinations(String location) {
		DataVaccinations data = new DataVaccinations(location);

		try {
			BufferedReader reader = Files.newBufferedReader(load("vaccinations.csv"));
			String rowString;

			while ((rowString = reader.readLine()) != null) {
				String[] row = rowString.split(",");
				if (row[0].equals(location) && row[3].length() > 0) {
					data.add(converter.fromString(row[2]), Integer.parseInt(row[3]));
				}
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("ERROR: Failed to process data.");
			System.out.println(e);
		}

		return data;
	}

	/**
	 * Parse vaccination data per hundred people from the CSV and add it to the data structure.
	 * @param {String} location
	 * @return {DataVaccinationsPerHundred} vaccination data per hundred people
	 */
	static public DataVaccinationsPerHundred vaccinationsPerHundred(String location) {
		DataVaccinationsPerHundred data = new DataVaccinationsPerHundred(location);

		try {
			BufferedReader reader = Files.newBufferedReader(load("vaccinations.csv"));
			String rowString;

			while ((rowString = reader.readLine()) != null) {
				String[] row = rowString.split(",");
				if (row[0].equals(location) && row[8].length() > 0) {
					data.add(converter.fromString(row[2]), Float.parseFloat(row[8]));
				}
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("ERROR: Failed to process data.");
			System.out.println(e);
		}

		return data;
	}

	/**
	 * Parse vaccination data of people fully vaccinated from the CSV and add it to the data structure.
	 * @param {String} location
	 * @return {DataPeopleVaccinated} people fully vaccinated data
	 */
	static public DataPeopleVaccinated peopleVaccinated(String location) {
		DataPeopleVaccinated data = new DataPeopleVaccinated(location);

		try {
			BufferedReader reader = Files.newBufferedReader(load("vaccinations.csv"));
			String rowString;

			while ((rowString = reader.readLine()) != null) {
				String[] row = rowString.split(",");
				if (row[0].equals(location) && row[5].length() > 0) {
					data.add(converter.fromString(row[2]), Integer.parseInt(row[5]));
				}
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("ERROR: Failed to process data.");
			System.out.println(e);
		}

		return data;
	}

	/**
	 * Parse daily vaccination data from the CSV and add it to the data structure.
	 * @param {String} location
	 * @return {DataDailyVaccinations} daily vaccination data
	 */
	static public DataDailyVaccinations dailyVaccinations(String location) {
		DataDailyVaccinations data = new DataDailyVaccinations(location);

		try {
			BufferedReader reader = Files.newBufferedReader(load("vaccinations.csv"));
			String rowString;

			while ((rowString = reader.readLine()) != null) {
				String[] row = rowString.split(",");
				if (row[0].equals(location) && row[7].length() > 0) {
					data.add(converter.fromString(row[2]), Integer.parseInt(row[7]));
				}
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("ERROR: Failed to process data.");
			System.out.println(e);
		}

		return data;
	}

	/**
	 * Parse daily vaccination data per million people from the CSV and add it to the data structure.
	 * @param {String} location
	 * @return {DataDailyVaccinations} daily vaccination data per million people
	 */
	static public DataDailyVaccinationsPerM dailyVaccinationsPerM(String location) {
		DataDailyVaccinationsPerM data = new DataDailyVaccinationsPerM(location);

		try {
			BufferedReader reader = Files.newBufferedReader(load("vaccinations.csv"));
			String rowString;

			while ((rowString = reader.readLine()) != null) {
				String[] row = rowString.split(",");
				if (row.length == 12 && row[0].equals(location) && row[11].length() > 0) {
					data.add(converter.fromString(row[2]), Integer.parseInt(row[11]));
				}
			}

			reader.close();
		} catch (Exception e) {
			System.out.println("ERROR: Failed to process data.");
			System.out.println(e);
		}

		return data;
	}
}
