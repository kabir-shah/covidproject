package covidproject;

import java.io.InputStream;
import java.io.BufferedReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import covidproject.DataVaccinations;

public class Data {
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

	static public DataVaccinations vaccinations() {
		DataVaccinations data = new DataVaccinations();

		try {
			BufferedReader reader = Files.newBufferedReader(load("vaccinations.csv"));
			String rowString;

			while ((rowString = reader.readLine()) != null) {
				String[] row = rowString.split(",");
				if (row[0].equals("United States") && row[3].length() > 0) {
					data.add(row[2], Integer.parseInt(row[3]));
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
