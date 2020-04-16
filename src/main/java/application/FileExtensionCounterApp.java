package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import fileutilities.InMemoryFileExtensionCounter;
import fileutilities.FileExtensionCounter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileExtensionCounterApp {

	private static Logger logger = LoggerFactory.getLogger(FileExtensionCounterApp.class);
	private FileExtensionCounter fileExtCounter = new InMemoryFileExtensionCounter();

	private String intputFilePath;

	public void loadApplicationConfig(String configFileName) throws FileNotFoundException {

		Gson gson = new Gson();

		// convert JSON file to map
		Map<?, ?> configMap = gson.fromJson(new FileReader(configFileName), Map.class);

		String inputDirectory = (String) configMap.get("file-location");
		String inputFileName = (String) configMap.get("file-name");

		intputFilePath = inputDirectory + System.getProperty("file.separator") + inputFileName;

	}

	public void printFileExtensionSummary() {

		List<String> fileExtenisionCountsummaryList = fileExtCounter.getFileExtensionSummary();

		for (int i = 0; i < fileExtenisionCountsummaryList.size(); i++) {
			if (i == fileExtenisionCountsummaryList.size() - 1)
				logger.info(fileExtenisionCountsummaryList.get(i));
			else
				logger.info(fileExtenisionCountsummaryList.get(i) + System.getProperty("line.separator"));
		}

	}

	public void processData() {

		BufferedReader bfReader = null;

		try {

			bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(intputFilePath), "UTF-8"));

			String line;

			while ((line = bfReader.readLine()) != null) {

				try {

					// ignore blank line
					if (line.trim().isEmpty())
						continue;

					populateFileExtensionInfo(line);

				} catch (JsonSyntaxException ex) {

					logger.error("Inavlid JSON entry: " + line + " Details:" + ex.getMessage());
				} catch (Exception ex) {

					logger.error(
							"Encountered exception while reading json entry: " + line + " Details:" + ex.getMessage());
				}

			}

		} catch (Exception ex) {

			logger.error("Encountered exception while reading input file: " + intputFilePath + " Details:"
					+ ex.getMessage());

		} finally {
			if (bfReader != null) {
				try {
					bfReader.close();
				} catch (IOException ex) {
					logger.error("Error occured while closing the file stream for input file: " + intputFilePath
							+ " Details: " + ex.getMessage());
				}
			}
		}

	}

	public void populateFileExtensionInfo(String jsonObject) {

		Gson gson = new GsonBuilder().create();

		Map<?, ?> map = gson.fromJson(jsonObject, Map.class);

		String fileNameWithExtension = (String) map.get("nm"); // read the filename for the JSON object

		// Ignore the JSON entry as file extension missing in the file name
		if (fileNameWithExtension.indexOf('.') == -1) {
			return;
		}

		int indexOfDot = fileNameWithExtension.lastIndexOf('.');
		String fileExtension = fileNameWithExtension.substring(indexOfDot + 1);
		String fileName = fileNameWithExtension.substring(0, indexOfDot);

		fileExtCounter.loadFileExtensionData(fileExtension, fileName);

	}

	public FileExtensionCounter getFileExtCounter() {
		return fileExtCounter;
	}

}
