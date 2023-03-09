# Jelly Blocks

we have developed a Java program to playing Jelly Blocks, a sliding blocks puzzle
whose goal is to put toghether all boxes with the same color. 
Replication of the game https://funhtml5games.com/?play=jelly 

## **Demo time:**


https://user-images.githubusercontent.com/91057170/224086262-b5ac5d65-33e5-464a-9f25-b58c9444a48b.mp4




## Directory tree layout

```bash
src
├── main/java
│   ├── es/upm/pproject/jellyblocks
│   │   └── MVCmain.java
|   ├── es/upm/pproject/jellyblocks/controller
│   │   └── LevelController.java
|   ├── es/upm/pproject/jellyblocks/model
│   │   ├── Exceptions.java
│   │   ├── FileChecker.java
│   │   ├── Level.java
│   │   └── Movement.java
|   ├── es/upm/pproject/jellyblocks/view
│   │   ├── LevelView.java
│   │   └── undo.png
├── main/resources
|   ├── log4.properties
|   ├── es/upm/pproject/jellyblocks/levels
│   │   ├── levelSaved.txt
│   │   ├── level_1.txt
│   │   ├── level_2.txt
│   │   ├── level_3.txt
│   │   ├── level_4.txt
│   │   ├── level_5.txt
│   │   ├── level_6.txt
│   │   ├── level_7.txt
│   │   ├── level_8.txt
│   │   └── points.txt
└── test/java
    ├── es/upm/pproject/jellyblocks
    │   ├── FileCheckerTest.java
    |   └── InGameTests.java
    └── es/upm/pproject/jellyblocks/LevelTest
        ├── level_1.txt
        ├── level_2.txt
        ├── level_3.txt
        ├── level_4.txt
        ├── level_5.txt
        ├── level_6.txt
        └── level_7.txt
        
```

## Implementation

Our program is comprised of 6 classes, 8 txt files and 1 image.

```bash
MVCmain.java
├── getModel()
├── getView()
└── getController()
```

### getModel()
This method gets the level model.
**Returns:** the model of Level class.

### getView()
This method gets the level view.
**Returns:** the view of LevelView class.

### getController()
This method gets the level controller.
**Returns:** the controller of LevelController class.

```bash
LevelController.java
├── getLevelName()
├── getLevelRows()
├── getLevelColumns()
├── getLevelMap()
├── getLevelPath()
├── getPosX()
├── getPosY()
├── setPosX(int posX)
├── setPosY(int posY)
├── setLevelPath(String path)
├── setLevelSpecs()
├── createLevelMap()
├── setOriginalMap(char[][] level)
├── getSaveLevel()
├── setLevelMap(char[][] levelMap)
├── createGoodLevelView()
├── createSaveLevelView()
├── createBadLevelView()
├── checkFileNextExist()
├── levelCompleted()
├── getNLevel()
├── changeLevel()
├── closeLevelView()
├── closeMenuView()
├── setFrameMenu()
├── startGame()
├── loadGame()
├── exitGame()
├── keyPressed(KeyEvent e)
├── mouseClicked(MouseEvent e)
├── getPointsLevel()
├── getPointsGame()
├── setPunctuation(int game, int level)
└── undo()
```

### getLevelName()
This method gets the level name.
**Returns:** the level name.

### getLevelRows()
This method gets the number of rows in the matrix.
**Returns:** the number of rows.

### getLevelColumns()
This method gets the number of columns in the matrix.
**Returns:** the number of columns.

### getLevelMap()
This method gets the level map.
**Returns:** the matrix that is the level map.

### getLevelPath()
This method gets the level path.
**Returns:** the level path.

### getPosX()
This method gets the position of X.
**Returns:** a number that is the position of X.

### getPosY()
This method gets the position of Y.
**Returns:** a number that is the position of Y.

### setPosX(int posX)
This method changes the position of X by the new position entered.
**Receives one parameter:** the position of X.

### setPosY(int posY)
This method changes the position of Y by the new position entered.
**Receives one parameter:** the position of Y.

### setLevelPath(String path)
This method changes the path by the new path entered.
**Receives one parameter:** the path.

### setLevelSpecs()
This method changes the level specifications by the level path entered.

### createLevelMap()
This method creates the level map.
**Returns:** the created map that is a matrix of characters.

### setOriginalMap(char[][] level)
This method changes the map to the original map of the level.
**Receives one parameter:** the level.
	
### getSaveLevel()
This method gets the saved level.
**Returns:** the saved level.

### setLevelMap(char[][] levelMap)
This method changes the level map by the new level map entered.
**Receives one parameter:** the level map.

### createGoodLevelView()
This method creates a good view of the level. Enter all the characteristics so that the level works correctly.
	
### createSaveLevelView()
This method creates the view of the saved level. Enter all the characteristics of the previously saved level.

### createBadLevelView()
This method creates a bad view of the level. Enter only the feature that is visible, the frame.

### checkFileNextExist()
This method checks that there is a next level.
**Returns:** true if there is a next level.

### levelCompleted()
This method checks if the level has been completed.
**Returns:** true if the level has been completed.

### getNLevel() 
This method gets the number level.
**Returns:** the number level.

### changeLevel()
This method changes the level.

### closeLevelView()
This method closes the level.

### closeMenuView()
This method closes the menu.

### setFrameMenu()
This method changes the menu screen.

### startGame()
This method starts the game.

### loadGame()
This method loads the game.

### exitGame()
This method exits the game.

### keyPressed(KeyEvent e)
In this method, the class detects press a key as an event and treats it accordingly through the model.
**Receives one parameter:** a KeyEvent.
	
### mouseClicked()
This method detects that save game has been pressed and displays a message on the screen.
	
### getPointsLevel()
This method gets the level points.
**Returns:** the level points.
	
### getPointsGame()
This method gets the game points, the points of all levels.
**Returns:** the game points.
	
### setPunctuation(int game, int level)
This method changes the punctuation to a new punctuation entered.
**Receives two parameters:** the game points and the level points.

### undo()
This method rolls back a move.

```bash
Exceptions.java
├── FileNotExistException()
├── FileCantOpenException()
├── InvalidRowsException(String rows)
├── InvalidColumnsException(String columns)
├── InvalidDimensionsException()
├── InvalidBordersException()
├── InvalidMapException()
├── InvalidBoxesException(String box)
└── FlyingBoxesException()
```

### FileNotExistException()
When the file could not be found.

### FileCantOpenException()
When the file could not be opened.

### InvalidRowsException(String rows)
When the value of the rows is not a valid number of rows : format.
**Receives one parameter:** the rows.

### InvalidColumnsException(String columns)
When the value of the columns is not a valid number of columns : format.
**Receives one parameter:** the columns.

### InvalidDimensionsException()
when the map does not have the dimensions especified in the second line of the file.

### InvalidBordersException()
When the map is not properly closed at its edges.

### InvalidMapException()
When the map is not a valid map.

### InvalidBoxesException(String box)
When the map is not a valid map. There are not enough boxes of type box.
**Receives one parameter:** the box.

### FlyingBoxesException()
When the map got some flying boxes.

```bash
FileChecker.java
├── setFile(String pathFile)
├── String getLevelName()
├── getnRows()
├── getnColumns()
├── getFile()
├── checkAllErrors()
├── checkFileExist()
├── checkFileOpen()
├── checkAndAssignNumbers()
├── checkMapDimensions()
├── checkBorders()
├── checkMapChars()
├── countBoxes(int nColumns)
├── checkBoxes(Map<Character, Integer> map)
├── createMap()
└── checkFlyingBoxes(char [][] levelMap)
```

### setFile(String pathFile)
This method changes the path file to the new path file entered.
**Receives one parameter:** the path file.

### String getLevelName()
This method gets the level name.
**Returns:** the level name.

### getnRows()
This method gets the number of rows.
**Returns:** the number of rows.

### getnColumns()
This method gets the number of columns.
**Returns:** the number of columns.

### getFile()
This method gets the file.
**Returns:** the file.

### checkAllErrors()
This method checks all methods for detect errors.
**Return:** true if there are not any error.

### checkFileExist()
This method checks if the file exists.
**Exception raised:** if the file doesn´t exists or the file cannot be found.

### checkFileOpen()
This method checks if the file can be opened.
**Exception raised:** if the file cannot be opened.

### checkAndAssignNumbers()
This method checks if the file has a correct (>1) number of rows and columns and assign to global variables.
**Exception raised:** if the value of the columns is not a valid number of columns : format, if the value of the columns is not a valid number of columns : format, if the file cannot be found and if there is not a valid number of rows or columns.

### checkMapDimensions()
This method checks the dimensions of the file.
**Exception raised:** if the file cannot be found and if the map does not have the dimensions especified in the second line of the file.

### checkBorders()
This method checks file borders.
**Exception raised:** if the file cannot be found and if the map is not properly closed at its edges.

### checkMapChars()
This method checks if the file has characters not allowed.
**Exception raised:** if the file cannot be found and if the map is not a valid map.

### countBoxes(int nColumns)
This method checks the number a box appears in the file and add to a HashMap.
**Receives one parameter**: the columns.
**Returns:** the map with the boxes added.
**Exception raised:** if the file cannot be found and if the map is not a valid map.

### checkBoxes(Map<Character, Integer> map)
This method checks that the map characters are the ones indicated in the statement.
**Receives one parameter**: the map.
**Returns:** "" or Error: InvalidBoxesException.
**Exception raised:** if the map is not a valid map. There are not enough boxes of type box.

### createMap()
This method create the map based on the file data.
**Returns:** the created map.
**Exception raised:** if the file cannot be found.

### checkFlyingBoxes(char [][] levelMap)
This method checks if the map does not have flying boxes.
**Receives one parameter**: the level map.
**Returns:** true if the map does not have flying boxes.
**Exception raised:** if the map got some flying boxes.

```bash
Level.java
├── setController(LevelController controller)
├── setBoxes(Map<Character, Integer> boxes)
├── getName()
├── getRows()
├── getColumns()
├── getNLevel()
├── getLevelMap()
├── getPosX()
├── getPosY()
├── setPosX(int posX)
├── setPosY(int posY)
├── setLevelMap(char[][] levelMap)
├── setPath(String newLevel)
├── getPath()
├── changeLevel()
├── checkFileNextExist()
├── auxiliar()
├── loadGame()
├── startGame()
├── getGamePunctuation()
├── getLevelPunctuation()
├── getNLevedSaved()
├── setSpecs(String levelPath)
├── createMap()
├── checkAllErrors()
├── countBoxes()
├── movePairs(String movement, int x, int y)
├── movePairsUp(String movement, int x, int y)
├── move(String movement, int x, int y, boolean down)
├── levelCompleted()
├── undo()
├── keyPressed(KeyEvent e)
└── mouseClicked()
```

### setController(LevelController controller)
This method changes the controller.
**Receives one parameter:** the controller of LevelController class

### setBoxes(Map<Character, Integer> boxes)
This method changes the boxes in the map.
**Receives one parameter:** the boxes map.

### getName()
This method gets the level name.
**Returns:** the level name.

### getRows()
This method gets the number of rows in the matrix.
**Returns:** the number of rows.

### getColumns()
This method gets the number of columns in the matrix.
**Returns:** the number of columns.

### getNLevel() 
This method gets the number level.
**Returns:** the number level.

### getLevelMap()
This method gets the level map.
**Returns:** the matrix that is the level map.

### getPosX()
This method gets the position of X.
**Returns:** a number that is the position of X.

### getPosY()
This method gets the position of Y.
**Returns:** a number that is the position of Y.

### setPosX(int posX)
This method changes the position of X by the new position entered.
**Receives one parameter:** the position of X.

### setPosY(int posY)
This method changes the position of Y by the new position entered.
**Receives one parameter:** the position of Y.

### setLevelMap(char[][] levelMap)
This method changes the level map by the new level map entered.
**Receives one parameter:** the level map.

### setPath(String newLevel)
This method changes the level path by the new level path entered.
**Receives one parameter:** the path.

### getPath()
This method gets the level path.
**Returns:** the level path.

### changeLevel()
This method changes the level using the level path. Check that everything is ok and call the auxiliar() method. If there´s some error the method only shows the frame.

### checkFileNextExist()
This method checks, via the file path, that there is a next level.
**Returns:** true if there is a next level.
**Exception raised:** if the file does not exist.

### auxiliar()
This helper method creates de level map and enter all the characteristics so that the level works correctly.

### loadGame()
This method loads the game using the level path. Check that everything is ok and call the auxiliar() method. If there´s no saved game, the method shows a message: "No level has been saved yet".

### startGame()
This method starts the game using the level path. Check that everything is ok and call the auxiliar() method. If there´s some error the method only shows the frame.

### getGamePunctuation()
This method gets the game punctuation, the points of all levels.
**Returns:** the game punctuation.
**Exception raised:** if the file cannot be found and if there is not a valid number of rows or columns.
	
### getLevelPunctuation()
This method gets the level punctuation. 
**Returns:** the level punctuation.
**Exception raised:** if the file cannot be found and if there is not a valid number of rows or columns.

### getNLevedSaved()
This method gets the number of the saved level.
**Returns:** the number of the saved level.
**Exception raised:** if the file cannot be found and if there is not a valid number of rows or columns.

### setSpecs(String levelPath)
This method changes the level specifications by the level path entered.
**Receives one parameter:** the level path.
**Exception raised:** if the file cannot be found and if there is not a valid number of rows or columns.

### createMap()
This method create the map based on the file data.
**Returns:** the created map.
**Exception raised:** if the file cannot be found.

### checkAllErrors()
This method checks all methods for detect errors.
**Return:** true if there are not any error.

### countBoxes()
This method checks the number a box appears in the file and add to a HashMap.
**Returns:** the map with the boxes added.
**Exception raised:** if the file cannot be found and if the map is not a valid map.

### movePairs(String movement, int x, int y)
This private method detects if it has a box of the same color attached to its left or right and moves with it.
**Receives three parameters:** the movement, the position of X and the position of Y.

### movePairsUp(String movement, int x, int y)
This private method detects if it has a box of the same color is attached on top of it and moves with it.
**Receives three parameters:** the movement, the position of X and the position of Y.

### move(String movement, int x, int y, boolean down)
This private method moves the selected box and updates the map, putting nothing where the box was before and putting the box where there was nothing before.
**Receives four parameters:** the movement, the position of X, the position of Y and true if the box is down.

### levelCompleted()
This method checks if the level has been completed.
**Returns:** true if the level has been completed.

### undo()
This method rolls back a move.

### keyPressed(KeyEvent e)
In this method, the class detects press a key as an event and treats it accordingly through the model.
**Receives one parameter:** a KeyEvent.
	
### mouseClicked()
This method detects that save game button has been pressed and displays a message on the screen.

```bash
Movement.java
├── getFromX()
├── getFromY()
├── getToX()
├── getToY()
└── getMoveNumber()
```
### getFromX()
This method gets the position of x.
**Returns:** the position of x.

### getFromY()
This method gets the position of y.
**Returns:** the position of y.

### getToX()
This method gets the position to which x advances.
**Returns:** the position to which x advances.

### getToY()
This method gets the position to which y advances.
**Returns:** the position to which y advances.

### getMoveNumber()
This method gets the number of the move.
**Returns:** the number of the move.
	
```bash
LevelView.java
├── setController(LevelController controller)
├── setFrameMenu()
├── setFrame()
├── addSides()
├── addRightSide(JPanel pnGamePunctuation)
├── addLevelName()
├── addLevelPanel()
├── paintComponent(Graphics g)
├── addRestart()
├── setPunctuation(int game, int level)
├── makeVisible()
├── close()
├── closeMenu()
├── setLevelColumns(int levelColumns)
├── setLevelRows(int levelRows)
├── getBlockWidth()
├── getBlockHeight()
├── getStartGameButton()
├── getLoadGameButton()
├── getExitGameButton()
├── getSaveLevel()
├── getPointsLevel()
├── getPointsGame()
├── setLevelName(String levelName)
├── setOriginalMap(char[][] originalMap)
├── mouseClicked(MouseEvent e) : MouseButtonUndo
├── mouseClicked(MouseEvent e) : MouseButtonHome
├── mouseClicked(MouseEvent e) : MouseButtonSave
├── mouseClicked(MouseEvent e) : MouseButton
├── mouseClicked(MouseEvent e) : MyMouseListener
├── keyTyped(KeyEvent e)
├── keyPressed(KeyEvent e)
└── keyReleased(KeyEvent e)
```

### setController(LevelController controller)
This method changes the controller.
**Receives one parameter:** the controller of LevelController class

### setFrameMenu()
This method shows the initial screen of the game where all the main buttons are.

### setFrame()
This method shows the game screen where the user is going to play.

### addSides()
This method creates the borders of the punctuation and adds the elements on the sides of the game screen.

### addRightSide(JPanel pnGamePunctuation)
This method creates the borders of the punctuation and adds the elements on the sides of the game screen.
**Receives one parameter:** the JPanel.

### addLevelName()
This method adds the level name on the game screen.

### addLevelPanel()
This method add level panel, the game.

### paintComponent(Graphics g)
This method paints, in the associated JPanel, the map it receives as an argument.
**Receives one parameter:** the Graphics g.

### addRestart()
This method adds the button Restart on the screen.

### setPunctuation(int game, int level)
This method changes the punctuation to a new punctuation entered.
**Receives two parameters:** the game points and the level points.

### makeVisible()
This method makes the game visible.

### close()
This method closes the game.

### closeMenu()
This method closes the game menu.

### setLevelColumns(int levelColumns)
This method changes the number of level columns to the new number of level columns entered.
**Receives one parameter:** the number of level columns.

### setLevelRows(int levelRows)
This method changes the number of level rows to the new number of level rows entered.
**Receives one parameter:** the number of level rows.

### getBlockWidth()
This method gets the width of the block.
**Returns:** the width of the block.

### getBlockHeight()
This method gets the height of the block.
**Returns:** the height of the block.

### getStartGameButton()
This method gets the button to start the game.
**Returns:** the button to start the game.

### getLoadGameButton()
This method gets the button to load the game.
**Returns:** the button to load the game.

### getExitGameButton()
This method gets the button to exit the game.
**Returns:** the button to exit the game.

### getSaveLevel()
This method gets the saved map of the level.
**Returns:** the saved map of the level.

### getPointsLevel()
This method gets the points of the level.
**Returns:** the points of the level.

### getPointsGame()
This method gets the points of the game, the points of all levels.
**Returns:** the points of the game.

### setLevelName(String levelName)
This method changes level name to the new level name entered.
**Receives one parameter:** the level name.

### setOriginalMap(char[][] originalMap)
This method changes the map to the original map of the level.
**Receives one parameter:** the original map.

### mouseClicked(MouseEvent e)
This method belongs to the MouseButtonUndo class, detects that undo button has been pressed and go back one move in the game.
**Receives one parameter:** the MouseEvent e.

### mouseClicked(MouseEvent e)
This method belongs to the MouseButtonHome class, detects that home button has been pressed and show the initial screen of the game, the menu.
**Receives one parameter:** the MouseEvent e.

### mouseClicked(MouseEvent e)
This method belongs to the MouseButtonSave class, detects that save game button has been pressed and displays a message on the screen.
**Receives one parameter:** the MouseEvent e.

### mouseClicked(MouseEvent e)
This method belongs to the MouseButton class, detects that restart button has been pressed and restart the level.
**Receives one parameter:** the MouseEvent e.

### mouseClicked(MouseEvent e)
This method belongs to the MyMouseListener class, detects when the user clicks on a block and selects it to move it
**Receives one parameter:** the MouseEvent e.

### keyTyped(KeyEvent e)
This method does nothing.
**Receives one parameter:** the keyEvent e.

### keyPressed(keyEvent e)
In this method, the class detects press a key as an event and run the event, move the blocks.
**Receives one parameter:** the keyEvent e.

### keyReleased(KeyEvent e)
This method does nothing.
**Receives one parameter:** the keyEvent e.

## Resources

```bash
es/upm/pproject/jellyblocks/levels
├── levelSaved.txt
├── level_1.txt
├── level_2.txt
├── level_3.txt
├── level_4.txt
├── level_5.txt
├── level_6.txt
├── level_7.txt
├── level_8.txt
└── points.txt
```

This package contains the levels of the game, each level is a txt file that contains a matrix of characters, that being the map of the level.
It also contains a txt file where the points are and the txt file where the maps will be saved so that "load game" loads them.

## Testing

Our testing suite consists of 2 classes and 7 txt files.

```bash
└── FileCheckerTest.java
```

FileCheckerTest.java is made up of 12 tests where exceptions are mainly checked.

```bash
└── InGameTest.java
```

InGameTest.java is made up of 34 tests that check all the functionalities within the game.

```bash
es/upm/pproject/jellyblocks/LevelsTest
├── level_1.txt
├── level_2.txt
├── level_3.txt
├── level_4.txt
├── level_5.txt
├── level_6.txt
├── level_7.txt
├── level_8.txt
└── level_9.txt
```

This package is made up of 9 txt files, which are test levels for testing.
