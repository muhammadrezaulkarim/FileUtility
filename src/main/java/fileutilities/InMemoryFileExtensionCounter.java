package fileutilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryFileExtensionCounter implements FileExtensionCounter {

	private Map<String, Integer> fileExtensionUniqueFileCounter = new HashMap<String, Integer>();
	private Map<String, String> fileExtensionUniqueFiles = new HashMap<String, String>();

	public boolean loadFileExtensionData(String fileExtension, String fileName) {

		boolean dataLoaded = false;

		String uniqueFiles = fileExtensionUniqueFiles.get(fileExtension);

		// no entries for this file extension
		if (uniqueFiles == null) {
			fileExtensionUniqueFiles.put(fileExtension, fileName);
		} else {
			// do not load the data if the filename already exists
			if (uniqueFiles.indexOf(fileName) >= 0) {
				dataLoaded = false;
				return dataLoaded;
			}

			// store new filename as it does not exist
			uniqueFiles = uniqueFiles + ";" + fileName;
			fileExtensionUniqueFiles.put(fileExtension, uniqueFiles);
		}

		Integer uniqueFileCount = fileExtensionUniqueFileCounter.get(fileExtension);

		if (uniqueFileCount == null) {
			fileExtensionUniqueFileCounter.put(fileExtension, new Integer(1));
		} else {
			fileExtensionUniqueFileCounter.put(fileExtension, new Integer(uniqueFileCount + 1));
		}

		dataLoaded = true;

		return dataLoaded;
	}

	public List<String> getFileExtensionSummary() {

		List<String> fileExtenisionCountsummaryList = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : fileExtensionUniqueFileCounter.entrySet()) {
			fileExtenisionCountsummaryList.add(entry.getKey() + ": " + entry.getValue());
		}

		return fileExtenisionCountsummaryList;
	}

}
