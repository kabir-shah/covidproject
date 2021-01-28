package covidproject;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Data {
	static private void download(String url, String file) {
		try (InputStream input = URI.create(url).toURL().openStream()) {
			Files.copy(input, Paths.get("../data/", file), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			System.out.println("ERROR: Failed to download data.");
			System.out.println(e);
		}
	}

	static public void update() {
		download("https://github.com/owid/covid-19-data/blob/master/public/data/owid-covid-data.json?raw=true", "general.json");
	}

	static public void cases() {
		System.out.println("Get cases here");
	}
}
