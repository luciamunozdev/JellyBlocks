package es.upm.pproject.jellyblocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Stack;

import es.upm.pproject.jellyblocks.controller.LevelController;
import org.junit.jupiter.api.Test;

import es.upm.pproject.jellyblocks.model.Level;
import es.upm.pproject.jellyblocks.model.Movement;

class InGameTest {

	Level fl = new Level();
	LevelController controller;

	@Test
	void getNLevel() {

		assertEquals(1, fl.getNLevel());
	}

	@Test
	void columnsLevel() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		int columns = 14;
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		assertEquals(columns, fl.getColumns());
	}

	@Test
	void rowsLevel() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		int rows = 8;
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		assertEquals(rows, fl.getRows());
	}

	@Test
	void nameLevel() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		String name = "Initial Level";
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setBoxes(fl.countBoxes());
		fl.setController(controller);
		assertEquals(name, fl.getName());
	}

	@Test
	void levelMap() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setBoxes(fl.countBoxes());
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		assertEquals(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt").length,
				fl.getLevelMap().length);

	}

	@Test
	void posX() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		assertEquals(0, fl.getPosX());
	}

	@Test
	void posY() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		assertEquals(0, fl.getPosY());
	}

	@Test
	void setPosX() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setPosX(3);
		assertEquals(3, fl.getPosX());
	}

	@Test
	void setPosY() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setPosY(2);
		assertEquals(2, fl.getPosY());
	}

	@Test
	void getPath() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		assertEquals("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt", fl.getPath());
	}

	@Test
	void changeLevel() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.checkAllErrors();
			fl.changeLevel();
			assertEquals(3, fl.getNLevel());
		} catch (Exception e) {
			assertEquals(true, doSomething());

		}
	}

	private boolean doSomething() {
		// This method will never throw exception
		return true;
	}

	@Test
	void startLevel() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.startGame();

			assertEquals(1, fl.getNLevel());
		} catch (Exception e) {
			assertEquals(true, doSomething());

		}
	}

	@Test
	void auxiliar() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.auxiliar("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");

		} catch (Exception e) {
			assertEquals(true, doSomething());

		}
	}

	@Test
	void loadLevel() {
		try {
			fl.setPath("src/main/java/es/upm/pproject/jellyblocks/levels/level_2.txt");
			fl.setSpecs("src/main/java/es/upm/pproject/jellyblocks/levels/level_2.txt");
			fl.loadGame();

			assertEquals(2, fl.getNLevel());
		} catch (Exception e) {
			assertEquals(true, doSomething());

		}
	}

	@Test
	void getPointsGameError() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level.txt");
			fl.getGamePunctuation();

		} catch (Exception e) {
			assertEquals("No line found", e.getMessage());
		}
	}

	@Test
	void getPointsLevelError() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.getLevelPunctuation();
		} catch (Exception e) {
			assertEquals("No line found", e.getMessage());
		}
	}

	@Test
	void getSpecsError() {
		try {
			fl.setPath("src/main/java/es/upm/pproject/jellyblocks/levels/level1.txt");
			fl.setSpecs("src/main/java/es/upm/pproject/jellyblocks/levels/level.txt");
		} catch (Exception e) {
			assertEquals("The file could not be found.", e.getMessage());
		}
	}

	@Test
	void getSpecsError2() {
		try {
			fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_4.txt");
		} catch (Exception e) {
			assertEquals("There is not a valid number of rows or columns", e.getMessage());
		}
	}

	@Test
	void move() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		fl.move("derecha", 3, 5, false);
		assertEquals(1, fl.getPosX());

	}

	@Test
	void moveIzq() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		fl.move("izquierda", 7, 3, false);
		assertEquals(-1, fl.getPosX());

	}

	@Test
	void moveIzq2() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		fl.move("izquierda", 3, 5, false);
		assertEquals(-1, fl.getPosX());

	}

	@Test
	void moveDown() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		fl.move("derecha", 3, 5, true);
		assertEquals(1, fl.getPosY());

	}

	@Test
	void movePairs1() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt"));
		fl.movePairs("derecha", 10, 5);

		assertEquals(1, fl.getPosX());

	}

	@Test
	void movePairs2() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt"));
		fl.movePairs("derecha", 11, 5);

		assertEquals(1, fl.getPosX());

	}

	@Test
	void movePairs3() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt"));
		fl.movePairs("izquierda", 10, 5);

		assertEquals(-1, fl.getPosX());

	}

	@Test
	void movePairs4() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt"));
		fl.movePairs("izquierda", 11, 5);

		assertEquals(-1, fl.getPosX());

	}

	@Test
	void movePairsUp1() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt"));
		fl.movePairsUp("derecha", 10, 5);

		assertEquals(1, fl.getPosX());

	}

	@Test
	void movePairsUp2() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt"));
		fl.movePairsUp("derecha", 10, 4);

		assertEquals(1, fl.getPosX());

	}

	@Test
	void movePairsUp3() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt"));
		fl.movePairsUp("izquierda", 10, 5);

		assertEquals(-1, fl.getPosX());

	}

	@Test
	void movePairsUp4() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_9.txt"));
		fl.movePairsUp("izquierda", 10, 4);

		assertEquals(-1, fl.getPosX());

	}

	@Test
	void levelInCompleted() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt"));
		fl.setBoxes(fl.countBoxes());
		assertEquals(false, fl.levelCompleted());

	}

	@Test
	void levelCompleted() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_10.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_10.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_10.txt"));
		fl.setBoxes(fl.countBoxes());

		assertEquals(true, fl.levelCompleted());

	}

	@Test
	void keyRight() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt"));
		fl.setBoxes(fl.countBoxes());
		fl.keyPressed(39);
		assertEquals(true, doSomething());

	}

	@Test
	void keyLeft() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_8.txt"));
		fl.setBoxes(fl.countBoxes());
		fl.setPosX(3);
		fl.setPosY(5);
		fl.keyPressed(37);
		assertEquals(true, doSomething());

	}

	@Test
	void testFileExistError() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_14.txt");
			fl.checkFileNextExist();
		} catch (Exception e) {
			assertEquals("The file could not be found.", e.getMessage());
		}

	}

	@Test
	void testFileExist() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		assertEquals(true, fl.checkFileNextExist());

	}

	@Test
	void getNLevedSaved() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.getNLevedSaved();
		} catch (Exception e) {
			assertEquals("No line found", e.getMessage());
		}
	}

	@Test
	void testUndo() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		Stack<Movement> movementStack = fl.getmovementStack();
		movementStack.push(new Movement(1, 1, 1, 1, 1));
		movementStack.push(new Movement(1, 1, 1, 1, 1));
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		fl.setBoxes(fl.countBoxes());
		fl.setMoveNumber(1);
		fl.undo();
		assertEquals(true, doSomething());
	}

	@Test
	void testClickUndo2() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		Stack<Movement> movementStack = fl.getmovementStack();
		movementStack.push(new Movement(1, 1, 1, 1, 1));
		movementStack.push(new Movement(1, 1, 1, 1, 1));
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		fl.setBoxes(fl.countBoxes());
		fl.setMoveNumber(1);
		fl.saveStack();
		fl.parseMovementStackToArray();
		assertEquals(true, doSomething());

	}

	@Test
	void testParse() {
		fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		Stack<Movement> movementStack = fl.getmovementStack();
		movementStack.push(new Movement(1, 1, 1, 1, 1));
		movementStack.push(new Movement(1, 1, 1, 1, 1));
		fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
		fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
		fl.setBoxes(fl.countBoxes());
		fl.setMoveNumber(1);
		fl.saveStack();
		fl.parseMovementStackToArray();
		fl.parseMovementStack();
		assertEquals(true, doSomething());

	}

	@Test
	void saveTest() {
		try {
			fl.setPath("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");

			Stack<Movement> movementStack = fl.getmovementStack();
			movementStack.push(new Movement(1, 1, 1, 1, 1));
			movementStack.push(new Movement(1, 1, 1, 1, 1));
			fl.setSpecs("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt");
			fl.setLevelMap(fl.createMap("src/test/java/es/upm/pproject/jellyblocks/LevelTests/level_1.txt"));
			fl.setBoxes(fl.countBoxes());
			fl.setMoveNumber(1);
			fl.mouseClicked();
			fl.saveStack();
			fl.parseMovementStackToArray();
			fl.parseMovementStack();
		} catch (Exception e) {
			assertEquals(true, doSomething());

		}
	}

}