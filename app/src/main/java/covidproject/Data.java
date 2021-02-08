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
import covidproject.DateCoordinate;

public class Data {
	static private DateCoordinate converter = new DateCoordinate();

	static private Path load(String file) {
		return Paths.get("../data/", file);
	}

	static private void download(String url, String file) {
		try (InputStream input = URI.create(url).toURL().openStream()) {
			Files.copy(input, load(file), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println("ERROR: Failed to download data.");
			System.out.println(e);
		}
	}

	static public void update() {
		download("https://github.com/owid/covid-19-data/raw/master/public/data/vaccinations/vaccinations.csv", "vaccinations.csv");
	}

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
}
