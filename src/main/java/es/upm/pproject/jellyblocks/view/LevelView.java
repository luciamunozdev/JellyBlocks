package es.upm.pproject.jellyblocks.view;

import es.upm.pproject.jellyblocks.controller.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class LevelView {

	private static final int FRAME_WIDTH = 1600;
	private static final int FRAME_HEIGHT = 900;

	private static final int LEVEL_WIDTH = FRAME_WIDTH * 10 / 16;
	private static final int LEVEL_HEIGHT = FRAME_HEIGHT * 7 / 10;

	private String levelName;
	private int levelColumns;
	private int levelRows;

	private static JFrame frame;
	private static JFrame frameMenu;

	private LevelController controller;

	private static String fontType = "MV Boli";
	private static String fontType2 = "Sans Serif";

	public void setController(LevelController controller) {
		this.controller = controller;
	}

	private static char[][] originalMap;
	char[][] saveMap;

	private int gamePunctuation = 0;
	private int levelPunctuation = 0;

	JButton btnStart = new JButton("START GAME");
	JButton btnExit = new JButton("CLOSE APP");
	JButton btnLoad = new JButton("LOAD GAME");
	JLabel lbGamePnts = new JLabel("", SwingConstants.CENTER);
	JLabel lbLevelPnts = new JLabel("", SwingConstants.CENTER);

	public void setFrameMenu() {
		JFrame frameMenu2 = new JFrame();

		frameMenu2.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frameMenu2.setTitle("Programming Project 2022");
		frameMenu2.setBounds(180, 100, FRAME_WIDTH, FRAME_HEIGHT);
		frameMenu2.setLayout(null);
		frameMenu2.setResizable(false);

		// PANEL CON EL NOMBRE DEL NIVEL
		JPanel pnGameName = new JPanel();
		pnGameName.setBackground(Color.white);
		pnGameName.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT / 10);
		pnGameName.setLayout(new BorderLayout());

		JLabel lbGameName = new JLabel("JELLY BLOCKS", SwingConstants.CENTER);
		lbGameName.setFont(new Font(fontType, Font.BOLD, 60));

		// PANEL DERECHO
		JPanel pnUndo = new JPanel();
		pnUndo.setBackground(Color.white);
		pnUndo.setBounds(FRAME_WIDTH * 13 / 16, FRAME_HEIGHT / 10, FRAME_WIDTH * 3 / 16, FRAME_HEIGHT * 7 / 10);

		// PANEL IZQUIERDO
		JPanel pnIzqdo = new JPanel();
		pnIzqdo.setBackground(Color.white);
		pnIzqdo.setBounds(0, FRAME_HEIGHT / 10, FRAME_WIDTH * 3 / 16, FRAME_HEIGHT * 7 / 10);

		// PANEL CONTENEDOR BOTONES
		JPanel pnButtons = new JPanel();
		pnButtons.setBackground(Color.white);
		pnButtons.setBounds(FRAME_WIDTH * 3 / 16, (FRAME_HEIGHT / 10), FRAME_WIDTH * 10 / 16, FRAME_HEIGHT * 7 / 10);
		pnButtons.setLayout(null);

		int widthPnButtons = pnButtons.getWidth();
		int heightPnButtons = pnButtons.getHeight();

		// BOTON START (SUPERIOR)
		btnStart.setFont(new Font(fontType, Font.BOLD, 30));
		btnStart.setBounds(widthPnButtons / 4, 10 + 50, widthPnButtons / 2, heightPnButtons / 5);
		btnStart.setBackground(Color.green);
		btnStart.setFocusable(false);

		// BOTON LOAD (INTERMEDIO)
		btnLoad.setFont(new Font(fontType, Font.BOLD, 30));
		btnLoad.setBounds(widthPnButtons / 4, 50 + 30 + heightPnButtons / 5, widthPnButtons / 2, heightPnButtons / 5);
		btnLoad.setBackground(Color.cyan);
		btnLoad.setFocusable(false);

		// BOTON EXIT (INFERIOR)
		btnExit.setFont(new Font(fontType, Font.BOLD, 30));
		btnExit.setBounds(widthPnButtons / 4, 50 + 50 + (heightPnButtons / 5) * 2, widthPnButtons / 2,
				heightPnButtons / 5);
		btnExit.setBackground(new Color(248, 50, 50));
		btnExit.setFocusable(false);

		// PANEL AUTORES
		JPanel pnBtnText = new JPanel();
		pnBtnText.setBounds(0, FRAME_HEIGHT * 8 / 10, FRAME_WIDTH, FRAME_HEIGHT * 80 / 500);
		pnBtnText.setBackground(Color.white);

		String name1 = "Guillermo Vargas Hidalgo<br>";
		String name2 = "Lucía Muñoz Martínez<br>";
		String name3 = "Jorge Verdugo Arroyo<br>";
		String name4 = "Fernando Fernández Álvarez<br>";

		JLabel lbBtnText = new JLabel();
		lbBtnText.setText("<html><style>body{text-align:center;}</style><body>Authors:<br>" + name1 + name2 + name3
				+ name4 + "</body></html>");
		lbBtnText.setFont(new Font(fontType, Font.PLAIN, 20));

		pnBtnText.add(lbBtnText);

		pnButtons.add(btnStart);
		pnButtons.add(btnLoad);
		pnButtons.add(btnExit);

		pnGameName.add(lbGameName);

		frameMenu2.add(pnButtons);
		frameMenu2.add(pnGameName);
		frameMenu2.add(pnIzqdo);
		frameMenu2.add(pnUndo);
		frameMenu2.add(pnBtnText);

		frameMenu2.setVisible(true);
		LevelView.frameMenu = frameMenu2;

	}

	// Creamos el frame al que se añadirán cosas
	public static void setFrame() {
		JFrame frame2 = new JFrame();
		frame2.setTitle("Programming Project 2022");
		frame2.setBackground(Color.cyan);
		frame2.setBounds(180, 100, FRAME_WIDTH, FRAME_HEIGHT);
		frame2.setDefaultCloseOperation(3);
		frame2.setResizable(false);
		frame2.setLayout(null);
		LevelView.frame = frame2;
	}

	public void addSides() {
		JPanel pnGamePunctuation = new JPanel();
		pnGamePunctuation.setBackground(Color.white);
		pnGamePunctuation.setBounds(FRAME_WIDTH * 13 / 16, FRAME_HEIGHT / 10, FRAME_WIDTH * 3 / 16,
				FRAME_HEIGHT * 7 / 10);
		pnGamePunctuation.setLayout(null);

		Border borderSides = BorderFactory.createLineBorder(Color.black, 3);

		JPanel pnText = new JPanel();
		pnText.setBounds(75, 0, 150, 100);
		pnText.setBackground(Color.white);
		pnText.setLayout(new BorderLayout());
		JLabel lbScore = new JLabel("<html><center>GAME<br>SCORE</center></html>", SwingConstants.CENTER);
		lbScore.setFont(new Font(fontType2, Font.BOLD, 35));
		pnText.add(lbScore, BorderLayout.CENTER);
		pnGamePunctuation.add(pnText);

		JPanel pnPoints = new JPanel();
		pnPoints.setBounds(50, 100, 200, 150);
		pnPoints.setBackground(new Color(234, 255, 255));
		pnPoints.setLayout(new BorderLayout());

		lbGamePnts.setFont(new Font(fontType2, Font.BOLD, 90));
		lbGamePnts.setText(gamePunctuation + "");

		pnPoints.add(lbGamePnts, BorderLayout.CENTER);
		pnGamePunctuation.add(pnPoints);

		pnPoints.setBorder(borderSides);
		pnGamePunctuation.add(pnPoints);
		addRightSide(pnGamePunctuation);
	}

	public void addRightSide(JPanel pnGamePunctuation) {

		Border borderSides = BorderFactory.createLineBorder(Color.black, 3);

		JPanel pnTextLevel = new JPanel();
		pnTextLevel.setBounds(75, 270, 150, 100);
		pnTextLevel.setBackground(Color.white);
		pnTextLevel.setLayout(new BorderLayout());
		JLabel lbLevelScore = new JLabel("<html><center>LEVEL<br>SCORE</center></html>", SwingConstants.CENTER);
		lbLevelScore.setFont(new Font(fontType2, Font.BOLD, 35));
		pnTextLevel.add(lbLevelScore, BorderLayout.CENTER);
		pnGamePunctuation.add(pnTextLevel);

		JPanel pnLevelPoints = new JPanel();
		pnLevelPoints.setBounds(50, 370, 200, 150);
		pnLevelPoints.setBackground(new Color(234, 255, 255));
		pnLevelPoints.setLayout(new BorderLayout());

		lbLevelPnts.setFont(new Font(fontType2, Font.BOLD, 90));
		lbLevelPnts.setText(levelPunctuation + "");

		pnLevelPoints.add(lbLevelPnts, BorderLayout.CENTER);
		pnLevelPoints.setBorder(borderSides);

		pnGamePunctuation.add(pnLevelPoints);

		frame.add(pnGamePunctuation);

		// -----------------------------
		JPanel pnUndo = new JPanel();
		pnUndo.setBackground(Color.white);
		pnUndo.setBounds(0, FRAME_HEIGHT / 10, FRAME_WIDTH * 3 / 16, FRAME_HEIGHT * 7 / 10);
		pnUndo.setLayout(null);

		JPanel pnUndoText = new JPanel();
		pnUndoText.setBounds(50, 105, 200, 60);
		pnUndoText.setBackground(Color.white);
		pnUndoText.setLayout(new BorderLayout());
		JLabel lbUndo = new JLabel("UNDO", SwingConstants.CENTER);
		lbUndo.setFont(new Font(fontType2, Font.BOLD, 35));
		pnUndoText.add(lbUndo, BorderLayout.CENTER);
		pnUndo.add(pnUndoText);

		JButton pnSymbol = new JButton();
		pnSymbol.addMouseListener(new MouseButtonUndo());
		pnSymbol.setFocusable(false);
		pnSymbol.setBounds(75, 165, 150, 125);
		pnSymbol.setBackground(new Color(234, 255, 255));
		pnSymbol.setLayout(new BorderLayout());

		ImageIcon image = new ImageIcon(new ImageIcon(getClass().getResource("undo.png")).getImage()
				.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		JLabel lbSymbol = new JLabel("", SwingConstants.CENTER);
		lbSymbol.setIcon(image);
		pnSymbol.add(lbSymbol, BorderLayout.CENTER);
		pnUndo.add(pnSymbol);

		pnSymbol.setBorder(borderSides);
		pnUndo.add(pnSymbol);

		JButton pnSaveGame = new JButton();
		pnSaveGame.setBounds(50, 305, 200, 100);
		pnSaveGame.setBackground(new Color(148, 243, 109));
		pnSaveGame.setBorder(borderSides);
		pnSaveGame.setLayout(new BorderLayout());

		JLabel lbHome = new JLabel("SAVE GAME", SwingConstants.CENTER);
		lbHome.setFont(new Font(fontType2, Font.BOLD, 25));

		pnSaveGame.add(lbHome, BorderLayout.CENTER);
		pnSaveGame.addMouseListener(new MouseButtonSave());
		pnSaveGame.setFocusable(false);
		pnUndo.add(pnSaveGame);

		JButton pnHome = new JButton();
		pnHome.setBounds(50, 415, 200, 100);
		pnHome.setBackground(Color.orange);
		pnHome.setBorder(borderSides);
		pnHome.setLayout(new BorderLayout());

		JLabel lbSaveGame = new JLabel("HOME", SwingConstants.CENTER);
		lbSaveGame.setFont(new Font(fontType2, Font.BOLD, 25));

		pnHome.add(lbSaveGame, BorderLayout.CENTER);
		pnUndo.add(pnHome);
		pnHome.addMouseListener(new MouseButtonHome());
		pnHome.setFocusable(false);

		frame.add(pnUndo);
	}

	public void addLevelName() {
		JPanel pnLevelName = new JPanel();
		pnLevelName.setBackground(Color.white);
		pnLevelName.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT / 10);
		pnLevelName.setLayout(new BorderLayout());

		JLabel lbLevelName = new JLabel(levelName, SwingConstants.CENTER);
		lbLevelName.setFont(new Font(fontType, Font.PLAIN, 60));
		pnLevelName.add(lbLevelName);
		frame.add(pnLevelName);
	}

	// Añade el panel del nivel (el juego)
	public void addLevelPanel() {

		HashMap<Character, Color> boxColours = new HashMap<>();

		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				SecureRandom newRand = new SecureRandom();
				char[][] levelMap = controller.getLevelMap();
				for (int y = 0; y < levelRows; y++) {
					for (int x = 0; x < levelColumns; x++) {
						if (levelMap[y][x] == '+') {
							g.setColor(Color.darkGray.brighter().brighter());
						} else if (levelMap[y][x] == '.') {
							g.setColor(new Color(234, 255, 255));
						} else {
							if (boxColours.containsKey(levelMap[y][x])) {
								g.setColor(boxColours.get(levelMap[y][x]));
							} else {
								Color newColour = new Color((int) (newRand.nextDouble() * 0x1000000));
								boxColours.put(levelMap[y][x], newColour);
								g.setColor(newColour);
							}
						}
						Rectangle2D rect = new Rectangle2D.Double(x * getBlockWidth(), y * getBlockHeight(),
								getBlockWidth(), getBlockHeight());
						g2.fill(rect);
					}
				}
			}

		};

		panel.addMouseListener(new MyMouseListener());
		panel.addKeyListener(new MyKeyListener());

		panel.setLayout(new GridLayout(levelRows, levelColumns));
		panel.setBounds(0, 0, LEVEL_WIDTH, LEVEL_HEIGHT);
		panel.setBackground(Color.red);
		panel.setFocusable(true);

		panel.setBounds(FRAME_WIDTH * 3 / 16, FRAME_HEIGHT / 10, LEVEL_WIDTH, LEVEL_HEIGHT);
		frame.add(panel);

	}

	public void addRestart() {
		JPanel pnBtnText = new JPanel();
		pnBtnText.setBounds(0, FRAME_HEIGHT * 8 / 10, FRAME_WIDTH, FRAME_HEIGHT * 80 / 500);
		pnBtnText.setLayout(new GridLayout(2, 2));

		String instructions1 = "Click or touch the blocks and move them left or right with arrow keys. ";
		String instructions2 = "Matching colored blocks will become attached.";
		String instructions3 = "Your goal is to connect all of the same colored blocks.";

		JPanel pnBtn = new JPanel();
		pnBtn.setLayout(null);
		pnBtn.setBackground(Color.white);

		JPanel pnText = new JPanel();
		pnText.setBackground(Color.white);

		JButton btRestart = new JButton("RESTART LEVEL");
		btRestart.setFocusable(false);
		btRestart.setBackground(Color.orange);
		btRestart.setFont(new Font("Roboto", Font.BOLD, 30));
		btRestart.setBounds(600, 10, 400, 50);
		btRestart.addMouseListener(new MouseButton());

		pnBtnText.add(pnBtn, BorderLayout.NORTH);
		pnBtnText.add(pnText, BorderLayout.SOUTH);

		JLabel lbBtnText = new JLabel();
		lbBtnText.setText("<html><style>body{text-align:center;}</style><body>" + instructions1 + "<br>" + instructions2
				+ instructions3 + "</body></html>");

		lbBtnText.setFont(new Font(fontType, Font.PLAIN, 20));

		pnBtn.add(btRestart);
		pnText.add(lbBtnText);
		pnBtnText.add(pnBtn);
		pnBtnText.add(pnText);
		frame.add(pnBtnText);
	}

	// -----------------------------------------------------------------------------------------------------------
	public void setPunctuation(int game, int level) {
		this.gamePunctuation = game;
		this.levelPunctuation = level;
	}

	public void makeVisible() {
		frame.setVisible(true);
	}

	public void close() {
		frame.dispose();
	}

	public void closeMenu() {
		frameMenu.dispose();
	}

	public void setLevelColumns(int levelColumns) {
		this.levelColumns = levelColumns;
	}

	public void setLevelRows(int levelRows) {
		this.levelRows = levelRows;
	}

	public double getBlockWidth() {
		return LEVEL_WIDTH / (double) levelColumns;
	}

	public double getBlockHeight() {
		return LEVEL_HEIGHT / (double) levelRows;
	}

	public JButton getStartGameButton() {
		return btnStart;
	}

	public JButton getLoadGameButton() {
		return btnLoad;
	}

	public JButton getExitGameButton() {
		return btnExit;
	}

	public char[][] getSaveLevel() {
		return saveMap;
	}

	public String getPointsLevel() {
		return String.valueOf(levelPunctuation);
	}

	public String getPointsGame() {
		return String.valueOf(gamePunctuation);
	}

	Border border = BorderFactory.createLineBorder(Color.black, 3);

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public void setOriginalMap(char[][] originalMap) {
		LevelView.originalMap = originalMap;
	}

	private class MouseButtonUndo extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			controller.undo();
			if (gamePunctuation > 0) {
				gamePunctuation--;

			}
			if (levelPunctuation > 0) {
				levelPunctuation--;

			}
			lbGamePnts.setText(gamePunctuation + "");
			lbLevelPnts.setText(levelPunctuation + "");

			frame.repaint();

		}

	}

	private class MouseButtonHome extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			controller.closeLevelView();
			controller.setFrameMenu();
		}
	}

	private class MouseButtonSave extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			controller.mouseClicked();
		}
	}

	private class MouseButton extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

			char[][] levelMap = controller.getLevelMap();

			for (int i = 0; i < levelRows; i++) {
				levelMap[i] = Arrays.copyOf(LevelView.originalMap[i], LevelView.originalMap[i].length);
			}

			controller.setLevelMap(levelMap);
			levelPunctuation = 0;
			lbLevelPnts.setText(levelPunctuation + "");
			frame.repaint();

		}

	}

	private class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

			double posX = Math.floor(e.getX() / getBlockWidth());
			double posY = Math.floor(e.getY() / getBlockHeight());

			controller.setPosX((int) posX);
			controller.setPosY((int) posY);
		}
	}

	private class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// Do nothing
		}

		@Override
		public void keyPressed(KeyEvent e) {
			controller.keyPressed(e.getKeyCode());
			if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
				gamePunctuation++;
				levelPunctuation++;
			}
			lbGamePnts.setText(gamePunctuation + "");
			lbLevelPnts.setText(levelPunctuation + "");
			frame.repaint();
			if (controller.levelCompleted() && controller.checkFileNextExist()) {
				JOptionPane.showMessageDialog(null, "This Level is completed", "CONGRATULATIONS!!",
						JOptionPane.INFORMATION_MESSAGE);
				controller.closeLevelView();

				controller.changeLevel();

				levelPunctuation = 0;
				lbLevelPnts.setText(levelPunctuation + "");
			} else if (controller.levelCompleted() && !controller.checkFileNextExist()) {
				JOptionPane.showMessageDialog(null, "<html><center>CONGRATULATIONS!!<br>You won the Game with "
						+ gamePunctuation + " Points..</center></html>", "END GAME", JOptionPane.INFORMATION_MESSAGE);

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Do nothing
		}

	}

}
