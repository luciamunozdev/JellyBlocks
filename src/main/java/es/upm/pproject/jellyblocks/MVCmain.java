package es.upm.pproject.jellyblocks;



import es.upm.pproject.jellyblocks.controller.LevelController;
import es.upm.pproject.jellyblocks.model.Level;
import es.upm.pproject.jellyblocks.view.LevelView;

public class MVCmain {

	private Level model;
	private LevelView view;
	private LevelController controller;

	public Level getModel() {
		return this.model;
	}

	public LevelView getView() {
		return this.view;
	}

	public LevelController getController() {
		return this.controller;
	}

	public static void main(String[] args) {

		// -------------------_CONTROLADOR NIVEL--------------------------

		LevelController controller = new LevelController(new Level(), new LevelView());

		controller.setFrameMenu();
		controller.startGame();
		controller.loadGame();

		controller.exitGame();

	}
}