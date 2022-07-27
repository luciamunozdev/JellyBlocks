package es.upm.pproject.jellyblocks.model;

import es.upm.pproject.jellyblocks.controller.*;
import es.upm.pproject.jellyblocks.model.Exceptions.FileNotExistException;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class Level {

	private String levelName;
	private String levelPath;
	private int levelRows;
	private int levelColumns;
	private char[][] levelMap;

	private int posX;
	private int posY;

	int s = 1;

	private LevelController controller;
	static String errorString = "ERROR";
	static String pointsFile = "src/main/resources/es/upm/pproject/jellyblocks/levels/points.txt";
	static String derechaString = "derecha";
	static String izquierdaString = "izquierda";
	static String pathGeneral =  "src/main/resources/es/upm/pproject/jellyblocks/levels/level_";
	
	private Map<Character, Integer> boxes;

	private int moveNumber = 0;

	private int tamMovStack = 0;

	
	static final Logger logger = Logger.getLogger(Level.class);

	Stack<Movement> movementStack = new Stack<>();

	public void setController(LevelController controller) {
		this.controller = controller;
	}

	public void setBoxes(Map<Character, Integer> boxes) {
		this.boxes = boxes;
	}

	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}

	public Stack<Movement> getmovementStack() {
		return movementStack;
	}

	public String getName() {
		return levelName;
	}

	public int getRows() {
		return levelRows;
	}

	public int getColumns() {
		return levelColumns;
	}

	public int getNLevel() {
		return s;
	}

	public char[][] getLevelMap() {
		return levelMap;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setLevelMap(char[][] levelMap) {
		this.levelMap = levelMap;
	}

	public void setPath(String newLevel) {
		this.levelPath = newLevel;
	}

	public String getPath() {
		return levelPath;
	}

	public void changeLevel() {

		String path;
		path =  pathGeneral + s++ + ".txt";

		setPath(path);

		if (checkAllErrors()) {

			auxiliar(path);

		} else {
			controller.createBadLevelView();
			JOptionPane.showMessageDialog(null, "This Level is wrong", errorString, JOptionPane.ERROR_MESSAGE);
			changeLevel();

		}

	}

	public boolean checkFileNextExist() {
		String path;
		int r = s;
		path =  pathGeneral + r + ".txt";
		File myFile = new File(path);
		Boolean res = false;
		try {
			if (!myFile.exists()) {
				throw new FileNotExistException();
			} else {
				res = true;

			}
		} catch (FileNotExistException e) {

			logger.error("[Error] [checkFileNextExist] FileNotExistException: " + e.getMessage());
		}
		return res;

	}

	public void auxiliar(String path) {
		setSpecs(path);
		createMap(path);
		controller.setOriginalMap(createMap(path));
		controller.setLevelMap(createMap(path));
		controller.closeMenuView();
		controller.createGoodLevelView();
	}

	public void loadGame() {
		String path;
		path = "src/main/resources/es/upm/pproject/jellyblocks/levels/levelSaved.txt";

		String pathRestart;

		setPath(path);
		if (checkAllErrors()) {
			controller.setPunctuation(getGamePunctuation(), getLevelPunctuation());
			parseMovementStack();
			s = getNLevedSaved() + 1;
			auxiliar(path);
			pathRestart =  pathGeneral + (s - 1) + ".txt";
			setSpecs(pathRestart);
			controller.setOriginalMap(createMap(pathRestart));

		} else {
			JOptionPane.showMessageDialog(null, "No level has been saved yet ", errorString, JOptionPane.ERROR_MESSAGE);
		}

	}

	public void startGame() {

		String path;
		s = 1;
		path =  pathGeneral + s++ + ".txt";

		setPath(path);

		if (checkAllErrors()) {
			setSpecs(path);
			controller.setOriginalMap(createMap(path));
			controller.setLevelMap(createMap(path));
			controller.createGoodLevelView();
		} else {
			controller.createBadLevelView();
			JOptionPane.showMessageDialog(null, "This Level is wrong", errorString, JOptionPane.ERROR_MESSAGE);
			changeLevel();
		}

	}

	public int getGamePunctuation() {
		File pointsFileCreated = new File(pointsFile);
		int points = 0;
		try (Scanner myReader = new Scanner(pointsFileCreated);) {

			String pointsLine = myReader.nextLine();
			points = Integer.parseInt(pointsLine);
		} catch (FileNotFoundException e) {
			logger.error("[Error] [getGamePunctuation] FileNotFoundException: " + e.getMessage());
		} catch (NumberFormatException e) {
			logger.error(
					"[Error] [getGamePunctuation] NumberFormatException: There is not a valid number of rows or columns");
		}
		return points;
	}

	public int getLevelPunctuation() {
		File pointsFileCreated = new File(pointsFile);
		int points = 0;
		try (Scanner myReader = new Scanner(pointsFileCreated);) {
			myReader.nextLine();
			String pointsLine = myReader.nextLine();
			points = Integer.parseInt(pointsLine);
		} catch (FileNotFoundException e) {
			logger.error("[Error] [getLevelPunctuation] FileNotFoundException: " + e.getMessage());
		} catch (NumberFormatException e) {
			logger.error(
					"[Error] [getLevelPunctuation] NumberFormatException: There is not a valid number of rows or columns");
		}
		return points;
	}

	public int getNLevedSaved() {
		File pointsFileCreated = new File(pointsFile);
		int level = 0;
		try (Scanner myReader = new Scanner(pointsFileCreated);) {
			myReader.nextLine();
			myReader.nextLine();
			String nLevel = myReader.nextLine();
			level = Integer.parseInt(nLevel);
		} catch (FileNotFoundException e) {
			logger.error("[Error] [getNLevedSaved] FileNotFoundException: " + e.getMessage());
		} catch (NumberFormatException e) {
			logger.error(
					"[Error] [getNLevedSaved] NumberFormatException: There is not a valid number of rows or columns");
		}
		return level;
	}

	public void setSpecs(String levelPath) {
		File level = new File(levelPath);
		try (Scanner myReader = new Scanner(level);) {
			String levelNameFile = myReader.nextLine();
			String stringRowColumns = myReader.nextLine();

			StringTokenizer stn = new StringTokenizer(stringRowColumns);

			int columns = Integer.parseInt(stn.nextToken());
			int rows = Integer.parseInt(stn.nextToken());

			// Asignamos los valores a las variables globales
			this.levelName = levelNameFile;
			this.levelRows = rows;
			this.levelColumns = columns;

		} catch (FileNotFoundException e) {
			logger.error("[Error] [setSpecs] FileNotFoundException: " + e.getMessage());
		} catch (NumberFormatException e) {
			logger.error("[Error] [setSpecs] NumberFormatException: There is not a valid number of rows or columns");
		}
	}

	public char[][] createMap(String levelPath) {
		File level = new File(levelPath);
		char[][] map = new char[levelRows][levelColumns];

		try {
			Scanner myReader = new Scanner(level);
			String line;
			// Leo 3 veces, para llegar a la primera linea del mapa
			myReader.nextLine();
			myReader.nextLine();
			for (int i = 0; myReader.hasNextLine(); i++) {
				line = myReader.nextLine();
				for (int j = 0; j < levelColumns; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			logger.error("[Error] [createMap] " + e.getMessage());
		}
		return map;
	}

	public boolean checkAllErrors() {

		FileChecker fc = new FileChecker();
		fc.setFile(levelPath);
		return fc.checkAllErrors();
	}

	public Map<Character, Integer> countBoxes() {
		FileChecker fc = new FileChecker();
		fc.setFile(levelPath);
		return fc.countBoxes(getColumns());

	}

	public void movePairs(String movement, int x, int y) {

		if (movement.equals(derechaString) && levelMap[y][x] >= 'a' && levelMap[y][x] <= 'z') {

			if (levelMap[y][x] == levelMap[y][x + 1] && levelMap[y][x + 2] == '.') {
				levelMap[y][x + 1] = levelMap[y][x];
				levelMap[y][x + 2] = levelMap[y][x + 1];
				levelMap[y][x] = '.';
				posX += 1;

				movementStack.push(new Movement(x + 1, y, x + 2, y, moveNumber));
				movementStack.push(new Movement(x, y, x + 1, y, moveNumber));
			} else if (levelMap[y][x - 1] == levelMap[y][x] && levelMap[y][x + 1] == '.') {
				levelMap[y][x + 1] = levelMap[y][x];
				levelMap[y][x] = levelMap[y][x - 1];
				levelMap[y][x - 1] = '.';
				posX += 1;

				movementStack.push(new Movement(x, y, x + 1, y, moveNumber));
				movementStack.push(new Movement(x - 1, y, x, y, moveNumber));
			}
		} else if (movement.equals(izquierdaString)) {
			if (levelMap[y][x] == levelMap[y][x + 1] && levelMap[y][x - 1] == '.') {
				levelMap[y][x - 1] = levelMap[y][x];
				levelMap[y][x] = levelMap[y][x + 1];
				levelMap[y][x + 1] = '.';
				posX -= 1;

				movementStack.push(new Movement(x, y, x - 1, y, moveNumber));
				movementStack.push(new Movement(x + 1, y, x, y, moveNumber));
			} else if (levelMap[y][x - 1] == levelMap[y][x] && levelMap[y][x - 2] == '.') {
				levelMap[y][x - 1] = levelMap[y][x];
				levelMap[y][x - 2] = levelMap[y][x - 1];
				levelMap[y][x] = '.';
				posX -= 1;

				movementStack.push(new Movement(x - 1, y, x - 2, y, moveNumber));
				movementStack.push(new Movement(x, y, x - 1, y, moveNumber));
			}
		}
	}

	public void movePairsUp(String movement, int x, int y) {

		if (movement.equals(derechaString) && levelMap[y][x] >= 'a' && levelMap[y][x] <= 'z') {

			if (levelMap[y][x] == levelMap[y - 1][x] && levelMap[y][x + 1] == '.' && levelMap[y - 1][x + 1] == '.') {

				levelMap[y][x + 1] = levelMap[y][x];
				levelMap[y - 1][x + 1] = levelMap[y - 1][x];
				levelMap[y][x] = '.';
				levelMap[y - 1][x] = '.';

				posX += 1;

				movementStack.push(new Movement(x, y, x + 1, y, moveNumber));
				movementStack.push(new Movement(x, y - 1, x + 1, y - 1, moveNumber));
			} else if (levelMap[y][x] == levelMap[y + 1][x] && levelMap[y][x + 1] == '.'
					&& levelMap[y + 1][x + 1] == '.') {

				levelMap[y][x + 1] = levelMap[y][x];
				levelMap[y + 1][x + 1] = levelMap[y + 1][x];
				levelMap[y][x] = '.';
				levelMap[y + 1][x] = '.';

				posX += 1;

				movementStack.push(new Movement(x, y, x + 1, y, moveNumber));
				movementStack.push(new Movement(x, y + 1, x + 1, y + 1, moveNumber));
			}
		} else if (movement.equals(izquierdaString)) {
			if (levelMap[y][x] == levelMap[y - 1][x] && levelMap[y][x - 1] == '.' && levelMap[y - 1][x - 1] == '.') {

				levelMap[y][x - 1] = levelMap[y][x];
				levelMap[y - 1][x - 1] = levelMap[y - 1][x];
				levelMap[y][x] = '.';
				levelMap[y - 1][x] = '.';

				posX -= 1;

				movementStack.push(new Movement(x, y, x - 1, y, moveNumber));
				movementStack.push(new Movement(x, y - 1, x - 1, y - 1, moveNumber));
			} else if (levelMap[y][x] == levelMap[y + 1][x] && levelMap[y][x - 1] == '.'
					&& levelMap[y + 1][x - 1] == '.') {

				levelMap[y][x - 1] = levelMap[y][x];
				levelMap[y + 1][x - 1] = levelMap[y + 1][x];
				levelMap[y][x] = '.';
				levelMap[y + 1][x] = '.';

				posX -= 1;

				movementStack.push(new Movement(x, y, x - 1, y, moveNumber));
				movementStack.push(new Movement(x, y + 1, x - 1, y + 1, moveNumber));
			}
		}
	}

	public void move(String movement, int x, int y, boolean down) {
		if (!down) {
			movePairs(movement, x, y);
			movePairsUp(movement, x, y);
			if (movement.equals(derechaString) && levelMap[y][x + 1] == '.' && levelMap[y + 1][x] != levelMap[y][x]
					&& levelMap[y - 1][x] != levelMap[y][x]) {
				levelMap[y][x + 1] = levelMap[y][x];
				movementStack.push(new Movement(x, y, x + 1, y, moveNumber));
				if (levelMap[y - 1][x] >= 'a' && levelMap[y - 1][x] <= 'z') {
					levelMap[y][x] = levelMap[y - 1][x];
					levelMap[y - 1][x] = '.';

					movementStack.push(new Movement(x, y - 1, x, y, moveNumber));
				} else {
					levelMap[y][x] = '.';
				}
				posX++;
			} else if (movement.equals(izquierdaString) && levelMap[y][x - 1] == '.'
					&& levelMap[y + 1][x] != levelMap[y][x] && levelMap[y - 1][x] != levelMap[y][x]) {
				levelMap[y][x - 1] = levelMap[y][x];
				movementStack.push(new Movement(x, y, x - 1, y, moveNumber));
				if (levelMap[y - 1][x] >= 'a' && levelMap[y - 1][x] <= 'z') {
					levelMap[y][x] = levelMap[y - 1][x];
					levelMap[y - 1][x] = '.';

					movementStack.push(new Movement(x, y - 1, x, y, moveNumber));
				} else {
					levelMap[y][x] = '.';
				}
				posX--;
			}
		} else {
			levelMap[y + 1][x] = levelMap[y][x];
			levelMap[y][x] = '.';
			posY++;
			movementStack.push(new Movement(x, y, x, y + 1, moveNumber));
		}
	}

	public boolean levelCompleted() {

		int numberGroups = boxes.size();
		boolean levelCompleted = true;
		int completedGroups = 0;
		for (Entry<Character, Integer> entry : boxes.entrySet()) {
			Character key = entry.getKey();
			int attachedBoxes = 0;
			for (int i = 0; i < levelRows; i++) {
				for (int j = 0; j < levelColumns; j++) {
					if (levelMap[i][j] == key) {
						boolean isKey = levelMap[i + 1][j] == key || levelMap[i - 1][j] == key
								|| levelMap[i][j + 1] == key || levelMap[i][j - 1] == key;

						if (isKey) {
							attachedBoxes++;
						}

						if (attachedBoxes == boxes.get(key)) {
							completedGroups++;
						}
					}
				}
			}
		}

		levelCompleted = completedGroups == numberGroups;

		return levelCompleted;

	}

	public void undo() {
		Movement lastMovement;
		while (!movementStack.isEmpty() && movementStack.peek().getMoveNumber() == moveNumber) {
			lastMovement = movementStack.pop();
			// SWAP CHARS
			int fromX = lastMovement.getFromX();
			int fromY = lastMovement.getFromY();
			int toX = lastMovement.getToX();
			int toY = lastMovement.getToY();
			char swapAux = levelMap[fromY][fromX];
			levelMap[fromY][fromX] = levelMap[toY][toX];
			levelMap[toY][toX] = swapAux;
		}
		moveNumber--;
	}

	public void keyPressed(int keyCode) {
		moveNumber++;
		String direccion;
		if (keyCode == KeyEvent.VK_RIGHT) {
			direccion = derechaString;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			direccion = izquierdaString;
		} else {
			return;
		}
		move(direccion, posX, posY, false);
		while (levelMap[posY + 1][posX] == '.') {
			move(direccion, posX, posY, true);
		}
	}

	public void mouseClicked() {

		try (FileWriter pnt = new FileWriter(pointsFile);
				BufferedWriter bwPnt = new BufferedWriter(pnt);
				FileWriter fw = new FileWriter("src/main/resources/es/upm/pproject/jellyblocks/levels/levelSaved.txt");
				BufferedWriter bw = new BufferedWriter(fw);) {
			bwPnt.write(controller.getPointsGame());
			bwPnt.newLine();
			bwPnt.write(controller.getPointsLevel());
			bwPnt.newLine();
			bwPnt.write(String.valueOf(getNLevel() - 1));
			bw.write(getName());
			bw.newLine();

			bw.write(getColumns() + " " + getRows());
			bw.newLine();

			for (int i = 0; i < levelRows; i++) {
				for (int j = 0; j < levelColumns; j++) {
					bw.write(levelMap[i][j]);
				}
				bw.newLine();
			}
			saveStack();
			parseMovementStackToArray();
			parseMovementStack();
			JOptionPane.showMessageDialog(null, "<html><center>LEVEL SAVED CORRECTLY</center></html>", "SAVE GAME",
					JOptionPane.INFORMATION_MESSAGE);
			
		} catch (IOException e) {
			logger.error("[Error] [mouseClicked] " + e.getMessage());
		}

	}

	public void saveStack() {
		try (FileWriter fw = new FileWriter("src/main/resources/es/upm/pproject/jellyblocks/levels/movementStack.txt");
				BufferedWriter bw = new BufferedWriter(fw);) {
			tamMovStack = movementStack.size();
			while (!movementStack.isEmpty()) {
				
				Movement movement = movementStack.pop();
				bw.write(movement.getFromX() + " " + movement.getFromY() + " " + movement.getToX() + " "
						+ movement.getToY() + " " + movement.getMoveNumber());
				bw.newLine();
			}
		} catch (IOException e) {
			logger.error("[Error] [mouseClickedUndo] " + e.getMessage());
		}
	}

	

	public void parseMovementStackToArray() {
		try (FileReader fr = new FileReader("src/main/resources/es/upm/pproject/jellyblocks/levels/movementStack.txt");
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			String [] arrString = new String[tamMovStack];

			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] movement = line.split(" ");
				int fromX = Integer.parseInt(movement[0]);
				int fromY = Integer.parseInt(movement[1]);
				int toX = Integer.parseInt(movement[2]);
				int toY = Integer.parseInt(movement[3]);
				int moveNumber2 = Integer.parseInt(movement[4]);
				arrString[i] = fromX + " " + fromY + " " + toX + " " + toY + " " + moveNumber2;
				i++;
			}
			String[] arrString2 = new String[tamMovStack];
			for (int j = 0; j < arrString.length; j++) {
				arrString2[j] = arrString[--tamMovStack];
			}
			saveArrayToFile(arrString2);

		} catch (IOException e) {
			logger.error("[Error] [parseMovementStack] " + e.getMessage());
		}
	}

	public void saveArrayToFile(String[] arrString) {
		try (FileWriter fw = new FileWriter("src/main/resources/es/upm/pproject/jellyblocks/levels/movementStackFinal.txt");
				BufferedWriter bw = new BufferedWriter(fw);) {
			for (int i = 0; i < arrString.length; i++) {
				bw.write(arrString[i]);
				bw.newLine();
			}
		} catch (IOException e) {
			logger.error("[Error] [saveArrayToFile] " + e.getMessage());
		}
	}


	
	
	public void parseMovementStack() {
		try (FileReader fr = new FileReader("src/main/resources/es/upm/pproject/jellyblocks/levels/movementStackFinal.txt");
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] movement = line.split(" ");
				int fromX = Integer.parseInt(movement[0]);
				int fromY = Integer.parseInt(movement[1]);
				int toX = Integer.parseInt(movement[2]);
				int toY = Integer.parseInt(movement[3]);
				int moveNumber2 = Integer.parseInt(movement[4]);
				this.moveNumber = moveNumber2;
				movementStack.push(new Movement(fromX, fromY, toX, toY, moveNumber2));
			}
		} catch (IOException e) {
			logger.error("[Error] [parseMovementStack] " + e.getMessage());
		}
	}

}
