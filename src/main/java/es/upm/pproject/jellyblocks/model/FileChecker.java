
package es.upm.pproject.jellyblocks.model;

import java.io.File;  
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import es.upm.pproject.jellyblocks.model.Exceptions.*;


public class FileChecker {
    
	private File myFile;
    private int nErrors = 0;
	
	private String levelName;
    private int nRows;
    private int nColumns;
	
    static final Logger logger = Logger.getLogger(FileChecker.class);
	    	
	public void setFile(String pathFile) {
		File newFile = new File(pathFile);
		myFile = newFile;
	}
	
    public String getLevelName() {
        return levelName;
    }

    public int getnRows() {
        return nRows;
    }

    public int getnColumns() {
        return nColumns;
    }
    
    public File getFile() {
    	return myFile;
    }

    int nErrorsAux;
    public boolean checkAllErrors() {
        Map<Character, Integer> map;
        checkFileExist();
    	checkFileOpen(); 
        checkAndAssignNumbers();
        nErrorsAux = nErrors;
        checkMapDimensions();
        if(nErrorsAux == nErrors) {
        	checkBorders();
        	checkMapChars();
            map = countBoxes(nColumns);
            checkBoxes(map);
            checkFlyingBoxes(createMap());
        } else {
        	logger.error("I have not checked the rest of the methods "
        			+ "because the method 'checkMapDimensions' returns at least "
        			+ "one error, and would produce a big exception");
        }
        
        
        if(nErrors > 0) {
        	logger.error("Hay " + nErrors + " errores");
        	return false;
        } else {
        	return true;	
        }
    }
    
    // this method checks if the file exists
    public void checkFileExist() {
        try{
            if (!this.myFile.exists()) {
                throw new FileNotExistException();
            }
        } catch (FileNotExistException e) {
        	nErrors++;
			logger.error("[Error] [checkFileExist] FileNotExistException: " + e.getMessage());
        }   
    }
    
    
    // this method checks if the file can be opened
    public void checkFileOpen() {
        try{
            if (!this.myFile.isFile()) {
                throw new FileCantOpenException();
            }

        } catch (FileCantOpenException e) {
        	nErrors++;
			logger.error("[Error] [checkFileOpen] FileCantOpenException: " + e.getMessage());
        }   
    }
    
    // This method checks if the file has a correct (>1) number of rows and columns and assign to global variables
	public void checkAndAssignNumbers(){
    	try(Scanner myReader = new Scanner(this.myFile);){
    		
            String levelName1 = myReader.nextLine();
            String rowsColumn1 = myReader.nextLine();
            
            StringTokenizer stn = new StringTokenizer(rowsColumn1);

            int nColumns1 = Integer.parseInt(stn.nextToken()); 
            int nRows1 = Integer.parseInt(stn.nextToken()); 

            if (nRows1 < 1) {
                throw new InvalidRowsException(Integer.toString(nRows1));
            } else if (nColumns1 < 1) {
                throw new InvalidColumnsException(Integer.toString(nColumns1));
            }            
            //Asignamos los valores a las variables globales
            this.levelName = levelName1;
            this.nRows = nRows1;
            this.nColumns = nColumns1;
        } catch (InvalidRowsException e) {
        	nErrors++;
			logger.error("[Error] [checkAndAssignNumbers] InvalidRowsException: " + e.getMessage());            
        } catch (InvalidColumnsException e) {
			nErrors++;
        	logger.error("[Error] [checkAndAssignNumbers] InvalidColumnsException: " + e.getMessage());           
        }catch (FileNotFoundException e) {
        	nErrors++;
			logger.error("[Error] [checkAndAssignNumbers] FileNotFoundException: " + e.getMessage());       
        } catch (NumberFormatException e) { 
        	nErrors++;
            String message = "There is not a valid number of rows or columns";
			logger.error("[Error] [checkAndAssignNumbers] NumberFormatException: " + message);           
        }
    }        


	public void checkMapDimensions(){
        try (Scanner myReader = new Scanner(this.myFile);){
            int rows =0;
            String line;
            //Leo 3 veces, para llegar a la primera linea del mapa
            myReader.nextLine();
            myReader.nextLine();
            rows = 0;
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                if (line.length() != nColumns){
                    throw new InvalidDimensionsException();
                }
                rows++;
            }
            if (rows != nRows){
                throw new InvalidDimensionsException();
            }
        } catch (InvalidDimensionsException e) {
        	nErrors++;
			logger.error("[Error] [checkMapDimensions] InvalidDimensionsException: " + e.getMessage());       
        } catch (FileNotFoundException e) {
        	nErrors++;
			logger.error("[Error] [checkMapDimensions] FileNotFoundException: " + e.getMessage());       
        }
    }
	
	
	public void checkBorders() {
        try(Scanner myReader = new Scanner(this.myFile);) {
            
            int rows =0;
            int columns = 0;
            String line;
            //Leo 3 veces, para llegar a la primera linea del mapa
            myReader.nextLine();
            myReader.nextLine();
            rows = 0;
            while (myReader.hasNextLine()) {
           
            	line = myReader.nextLine();
            	columns = 0;
            	// Comprobar techo y suelo
            	if(rows == 0 || rows == nRows) {
            		for (int i = 0; i < nColumns; i++) {
                        if (line.charAt(i) == '+') {
                            columns++;
                        }
                    }
                    if (columns != nColumns){
                        throw new InvalidBordersException();
                    }
                }
            	if(line.charAt(0) != '+' && line.charAt(nColumns-1) != '+') {
            		throw new InvalidBordersException();
            	}
            	rows++;
            }
        } catch (InvalidBordersException e) {
        	nErrors++;
			logger.error("[Error] [checkBorders] InvalidBordersException: " + e.getMessage());       
        } catch (FileNotFoundException e) {
        	nErrors++;
			logger.error("[Error] [checkBorders] FileNotFoundException: " + e.getMessage());       
        }
	}

    // This method checks if the file has characters not allowed
    public void checkMapChars(){
    	try(Scanner myReader = new Scanner(this.myFile);) { 
            String line;
            //Leo 3 veces, para llegar a la primera linea del mapa
            myReader.nextLine();
            myReader.nextLine();
            
            while (myReader.hasNextLine()) {

                line = myReader.nextLine();
                if(line.length() == nColumns) {
                	for (int i = 0; i < nColumns; i++) {
                        boolean cond = line.charAt(i) != '+' && line.charAt(i) != '.';
                        if (cond && !(line.charAt(i) >='a' && line.charAt(i) <= 'z')) {
                            throw new InvalidMapException();
                        }  
                    }  
                } else {
                    throw new InvalidMapException();
                }
                
            }
        } catch (InvalidMapException e) {
        	nErrors++;
			logger.error("[Error] [checkMapChars] InvalidMapException: " + e.getMessage());       
        } catch (FileNotFoundException e) {
        	nErrors++;
			logger.error("[Error] [checkMapChars] FileNotFoundException: " + e.getMessage());       
        }
    }
    
    // This method checks the number a box appears in the file and add to a HashMap
    public Map<Character, Integer> countBoxes(int nColumns){
        Map<Character, Integer> map = new HashMap<>();
        try( Scanner myReader = new Scanner(this.myFile);) {
           
            String line;
            //Leo 3 veces, para llegar a la primera linea del mapa
            myReader.nextLine();
            myReader.nextLine();

            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                if(line.length() == nColumns) {
                for (int i = 0; i < nColumns; i++) {
                    if (line.charAt(i) != '+' && line.charAt(i) != '.') {
                        if (map.containsKey(line.charAt(i))){
                            map.put(line.charAt(i), map.get(line.charAt(i)) + 1);
                        } else {
                            map.put(line.charAt(i), 1);
                        }
                    }
                }
                } else {
                    throw new InvalidMapException();
                }
            }
        } catch (FileNotFoundException e) {
        	nErrors++;
			logger.error("[Error] [countBoxes] FileNotFoundException: " + e.getMessage());             
        } catch (InvalidMapException e) {
        	nErrors++;
			logger.error("[Error] [checkMapChars] InvalidMapException: " + e.getMessage());       
        }
        return map;
    }

    public String checkBoxes(Map<Character, Integer> map){
        // checkear que los caracteres del mapa son los indicados en el enunciado
        try {
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() < 2){
                    throw new InvalidBoxesException(entry.getKey().toString());
                }
            }
        } catch (InvalidBoxesException e) {
        	nErrors++;
			logger.error("[Error] [checkBoxes] InvalidBoxesException: " + e.getMessage());       
            return "[Error] [checkBoxes] InvalidBoxesException: " + e.getMessage();
        }
        return "";
    }
    
    public char[][] createMap(){
		char [][] map= new char[nRows][nColumns];
		
        try(Scanner myReader = new Scanner(myFile);) {
           
            String line;
            //Leo 3 veces, para llegar a la primera linea del mapa
            myReader.nextLine();
            myReader.nextLine();
            int i = 0;
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                for(int j = 0; j < nColumns; j++) {
                	map[i][j] = line.charAt(j);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
        	logger.error("[Error] [createMap] " + e.getMessage());       
        }
        return map;
    }

    public boolean checkFlyingBoxes(char [][] levelMap) {
		boolean res = false;
		try {

			for(int i = 0; i < nRows; i++) {
				for(int j = 0; j < nColumns; j++) {
					if(levelMap[i][j] >= 'a' && levelMap[i][j] <= 'z' && levelMap[i+1][j] == '.') {
                        throw new FlyingBoxesException();
					}
				}
			}
			res = true;
		} catch (FlyingBoxesException e) {
			res = false;
			nErrors++;
			logger.error("[Error] [checkFlyingBoxes] " + e.getMessage());       
			
		}
		
		
		return res;
	}
    
}