package es.upm.pproject.jellyblocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;

import org.junit.jupiter.api.Test;

import es.upm.pproject.jellyblocks.model.FileChecker;

class FileCheckerTest {

	@Test
	void testFileExist() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("jellyblocks/src/test/java/es/upm/pproject/jellyblocks/levelTests/level.txt");
			fc.checkFileExist();
		} catch (Exception e) {
			assertEquals("The file could not be found.", e.getMessage());
		}

	}

	@Test
	void testFileOpen() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("jellyblocks/src/test/java/es/upm/pproject/jellyblocks/levelTests/level.txt");
			fc.checkFileOpen();
		} catch (Exception e) {
			assertEquals("The file could not be opened.", e.getMessage());
		}

	}

	@Test
	void testGetLevelName() {
		FileChecker fc = new FileChecker();
		fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fc.checkAllErrors();
		String name = "Initial Level";
		assertEquals(name, fc.getLevelName());
	}

	@Test
	void testGetnRows() {
		FileChecker fc = new FileChecker();
		fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fc.checkAllErrors();
		int rows = 8;
		assertEquals(rows, fc.getnRows());
	}

	@Test
	void testGetnColumns() {
		FileChecker fc = new FileChecker();
		fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fc.checkAllErrors();
		int columns = 14;
		assertEquals(columns, fc.getnColumns());
	}

	@Test
	void testGetInvalidRows() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_2.txt");
			fc.checkAllErrors();

		} catch (Exception e) {
			assertEquals("The value: \'" + -8 + "\' is not a valid number of rows (format)", e.getMessage());
		}
	}

	@Test
	void testGetInvalidColumns() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_3.txt");
			fc.checkAllErrors();

		} catch (Exception e) {
			assertEquals("The value: \'" + -5 + "\' is not a valid number of columns (format)", e.getMessage());
		}
	}
	
	@Test
	void testGetInvalidRC() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_4.txt");
			fc.checkAllErrors();

		} catch (Exception e) {
			assertEquals("There is not a valid number of rows or columns", e.getMessage());
		}
	}

	@Test
	void testGetFile() {
		File myFile = new File("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		FileChecker fc = new FileChecker();
		fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		assertEquals(myFile, fc.getFile());
	}
	



	@Test
	void testMapChars() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_5.txt");
			fc.checkAllErrors();
		} catch (Exception e) {
			assertEquals("The map is not a valid map", e.getMessage());
		}

	}

	@Test
	void testMapBorders() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_6.txt");
			fc.checkAllErrors();
		} catch (Exception e) {
			assertEquals("The map is not properly closed at its edges.", e.getMessage());
		}

	}
	
	@Test
	void testFlyingBoxes() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_7.txt");
			fc.checkAllErrors();
		} catch (Exception e) {
			assertEquals("The map got some flying boxes, be careful", e.getMessage());
		}

	}
	
	@Test
	void testCountBoxes() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level.txt");
			fc.checkAllErrors();
			fc.countBoxes(1);
		} catch (Exception e) {
			assertEquals("The file could not be found.", e.getMessage());
		}

	}
	@Test
	void testCountBoxes2() {
		try {
			FileChecker fc = new FileChecker();
			fc.setFile("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_11.txt");
			fc.checkAllErrors();
			fc.countBoxes(1);
		} catch (Exception e) {
			assertEquals("The map is not a valid map", e.getMessage());
		}

	}
}
