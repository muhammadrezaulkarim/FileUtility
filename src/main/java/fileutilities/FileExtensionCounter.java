package fileutilities;

import java.util.List;

public interface FileExtensionCounter {

	public boolean loadFileExtensionData(String fileExtension, String fileName);

	public List<String> getFileExtensionSummary();

}
