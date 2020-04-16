package solutions;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import application.FileExtensionCounterApp;

public class FileExtensionCounterAppTest {

	@Test
	public void testAllValid() {

		String APPLICATION_CONFIG_FILE = "app-config-all-valid.json";
		File file = new File(getClass().getClassLoader().getResource(APPLICATION_CONFIG_FILE).getFile());

		FileExtensionCounterApp app = null;
		try {
			app = new FileExtensionCounterApp();
			app.loadApplicationConfig(file.getAbsolutePath());
			app.processData();
			app.printFileExtensionSummary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<String> expectedListOutput = new ArrayList<String>();
		expectedListOutput.add("ext: 1");
		expectedListOutput.add("pdf: 1");

		assertEquals(expectedListOutput, app.getFileExtCounter().getFileExtensionSummary());
	}

	@Test
	public void testAllDuplicates() {

		String APPLICATION_CONFIG_FILE = "app-config-all-duplicate.json";
		File file = new File(getClass().getClassLoader().getResource(APPLICATION_CONFIG_FILE).getFile());

		FileExtensionCounterApp app = null;
		try {
			app = new FileExtensionCounterApp();
			app.loadApplicationConfig(file.getAbsolutePath());
			app.processData();
			app.printFileExtensionSummary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<String> expectedListOutput = new ArrayList<String>();
		expectedListOutput.add("ext: 1");

		assertEquals(expectedListOutput, app.getFileExtCounter().getFileExtensionSummary());
	}

	@Test()
	public void testAllInValid() {

		String APPLICATION_CONFIG_FILE = "app-config-all-invalid.json";
		File file = new File(getClass().getClassLoader().getResource(APPLICATION_CONFIG_FILE).getFile());

		FileExtensionCounterApp app = null;
		try {
			app = new FileExtensionCounterApp();
			app.loadApplicationConfig(file.getAbsolutePath());

			app.processData();
			app.printFileExtensionSummary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// empty list
		List<String> expectedListOutput = new ArrayList<String>();

		assertEquals(expectedListOutput, app.getFileExtCounter().getFileExtensionSummary());

	}

	@Test
	public void testFirstMissingShaAttribute() {

		String APPLICATION_CONFIG_FILE = "app-config-missing-quote-sha-attribute.json";
		File file = new File(getClass().getClassLoader().getResource(APPLICATION_CONFIG_FILE).getFile());

		FileExtensionCounterApp app = null;
		try {
			app = new FileExtensionCounterApp();
			app.loadApplicationConfig(file.getAbsolutePath());
			app.processData();
			app.printFileExtensionSummary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<String> expectedListOutput = new ArrayList<String>();
		expectedListOutput.add("ext: 1");
		expectedListOutput.add("pdf: 1");

		assertEquals(expectedListOutput, app.getFileExtCounter().getFileExtensionSummary());
	}

	@Test
	public void testFirstInvalid() {

		String APPLICATION_CONFIG_FILE = "app-config-first-json-object-unterminated.json";
		File file = new File(getClass().getClassLoader().getResource(APPLICATION_CONFIG_FILE).getFile());

		FileExtensionCounterApp app = null;
		try {
			app = new FileExtensionCounterApp();
			app.loadApplicationConfig(file.getAbsolutePath());
			app.processData();
			app.printFileExtensionSummary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<String> expectedListOutput = new ArrayList<String>();
		expectedListOutput.add("ext: 1");
		expectedListOutput.add("pdf: 1");

		assertEquals(expectedListOutput, app.getFileExtCounter().getFileExtensionSummary());
	}

	@Test
	public void testFirstTwoInValid() {

		String APPLICATION_CONFIG_FILE = "app-config-first-two-json-object-invalid.json";
		File file = new File(getClass().getClassLoader().getResource(APPLICATION_CONFIG_FILE).getFile());

		FileExtensionCounterApp app = null;
		try {
			app = new FileExtensionCounterApp();
			app.loadApplicationConfig(file.getAbsolutePath());
			app.processData();
			app.printFileExtensionSummary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<String> expectedListOutput = new ArrayList<String>();
		expectedListOutput.add("ext: 1");

		assertEquals(expectedListOutput, app.getFileExtCounter().getFileExtensionSummary());
	}

	@Test
	public void testInputFileEmpty() {

		String APPLICATION_CONFIG_FILE = "app-config-input-file-empty.json";
		File file = new File(getClass().getClassLoader().getResource(APPLICATION_CONFIG_FILE).getFile());

		FileExtensionCounterApp app = null;
		try {
			app = new FileExtensionCounterApp();
			app.loadApplicationConfig(file.getAbsolutePath());
			app.processData();
			app.printFileExtensionSummary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// list will be empty
		assertEquals(0, app.getFileExtCounter().getFileExtensionSummary().size());
	}

}
