package es.upm.pproject.jellyblocks.controller;


import es.upm.pproject.jellyblocks.model.Level;
import es.upm.pproject.jellyblocks.view.LevelView;

public class LevelController {

	private Level model;
	private LevelView view;

	public LevelController(Level model, LevelView view) {
		this.model = model;
		this.view = view;
		model.setController(this);
		view.setController(this);
	}

	public String getLevelName() {
		return model.getName();
	}

	public int getLevelRows() {
		return model.getRows();
	}

	public int getLevelColumns() {
		return model.getColumns();
	}

	public char[][] getLevelMap() {
		return model.getLevelMap();
	}

	public String getLevelPath() {
		return model.getPath();
	}

	public int getPosX() {
		return model.getPosX();
	}

	public int getPosY() {
		return model.getPosY();
	}

	public void setPosX(int posX) {
		model.setPosX(posX);
	}

	public void setPosY(int posY) {
		model.setPosY(posY);
	}


	public void setLevelPath(String path) {
		model.setPath(path);
	}

	public void setLevelSpecs() {
		model.setSpecs(getLevelPath());
	}

	public char[][] createLevelMap(String path) {
		return model.createMap(path);
	}

	public void setOriginalMap(char[][] level) {
		view.setOriginalMap(level);
	} 
	
	public char [][] getSaveLevel() {
		return view.getSaveLevel();
	}

	public void setLevelMap(char[][] levelMap) {
		model.setLevelMap(levelMap);
	}



	public void createGoodLevelView() {
		view.setFrame();
		view.setLevelRows(getLevelRows());
		view.setLevelColumns(getLevelColumns());
		view.setLevelName(getLevelName());
		model.setBoxes(model.countBoxes());
		view.addLevelName();
		view.addLevelPanel();
		view.addRestart();
		view.addSides();
		view.makeVisible();
	}
	
	public void createSaveLevelView() {
		view.setFrame();
		view.setLevelRows(8);
		view.setLevelColumns(14);
		view.addLevelName();
		view.addLevelPanel();
		view.addRestart();
		view.addSides();
		view.makeVisible();
	}

	public void createBadLevelView() {
		view.setFrame();
		view.makeVisible();
	}

	// -------------------------CHECKER-------------------------------------


	public boolean checkFileNextExist() {
		return model.checkFileNextExist();
	}

	

	public boolean levelCompleted() {
		return model.levelCompleted();
	}

	public int getNLevel() {
		return model.getNLevel();
	}

	public void changeLevel() {
		model.changeLevel();
	}

	public void closeLevelView() {
		view.close();
	}

	public void closeMenuView() {
		view.closeMenu();
	}

	// ---------------------------------------------------------------------

	public void setFrameMenu() {
		view.setFrameMenu();
	}

	public void startGame() {
		view.getStartGameButton().addActionListener(e -> {
			view.setPunctuation(0,0);
			model.startGame();
			closeMenuView();

		});

	}

	public void loadGame() {
			view.getLoadGameButton().addActionListener(e -> model.loadGame());
		
	}

	public void exitGame() {
		view.getExitGameButton().addActionListener(e -> closeMenuView());
	}



	public void keyPressed(int keyCode) {
		model.keyPressed(keyCode);
	}
	
	public void mouseClicked() {
		model.mouseClicked();
	}
	

	
	public String getPointsLevel() {
		return view.getPointsLevel();
	}
	
	public String getPointsGame() {
		return view.getPointsGame();
	}
	
	public void setPunctuation(int game, int level) {
		view.setPunctuation( game, level);
	}
	
	public void undo() {
		model.undo();
	}
	
}