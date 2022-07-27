package es.upm.pproject.jellyblocks.model;

public class Exceptions {
    
    private Exceptions() {
		
	}

    public static class FileNotExistException extends Exception {
		public FileNotExistException() {
            super("The file could not be found.");
        }
    }
    public static class FileCantOpenException extends Exception {
        public FileCantOpenException() {
            super("The file could not be opened.");
        }
    }
    
    static class InvalidRowsException extends Exception{
        public InvalidRowsException(String rows){
            super("The value: \'" + rows + "\' is not a valid number of rows (format)");
        }
    }

    static class InvalidColumnsException extends Exception{
        public InvalidColumnsException(String columns){
            super("The value: \'" + columns + "\' is not a valid number of columns (format)");
        }
    }
    
    static class InvalidDimensionsException extends Exception{
        public InvalidDimensionsException(){
            super("The map does not have the dimensions especified in the second line of the file.");
        }
    }
    
    static class InvalidBordersException extends Exception{
        public InvalidBordersException(){
            super("The map is not properly closed at its edges.");
        }
    }

    static class InvalidMapException extends Exception{
        public InvalidMapException(){
            super("The map is not a valid map");
        }
    }

    static class InvalidBoxesException extends Exception{
        public InvalidBoxesException(String box){
            super("The map is not a valid map. There are not enough boxes of type \'" + box + "\'");
        }
    }
    
    static class FlyingBoxesException extends Exception{
        public FlyingBoxesException(){
            super("The map got some flying boxes, be careful");
        }
    }

}
