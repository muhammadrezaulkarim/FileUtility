package application;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTester {

	private static Logger logger = LoggerFactory.getLogger(AppTester.class);
	private static String APPLICATION_CONFIG_FILE = "app-config.json";

	public static void main(String[] args) {

		try {

			FileExtensionCounterApp app = new FileExtensionCounterApp();

			try {
				app.loadApplicationConfig(APPLICATION_CONFIG_FILE);

			} catch (FileNotFoundException ex) {

				logger.error("Application configuration file could not loaded. Details:" + ex.getMessage());
				System.exit(0);
			}

			app.processData();

			app.printFileExtensionSummary();

		}

		catch (Exception ex) {

			logger.error("The application has encountered exception." + " Details: " + ex.getMessage());
		}

	}

}
