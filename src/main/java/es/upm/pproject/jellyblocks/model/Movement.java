package es.upm.pproject.jellyblocks.model;

public class Movement {
	private int fromX;
	private int fromY;
	private int toX;
	private int toY;
	private int moveNumber;
	
	public Movement(int fromX, int fromY, int toX, int toY, int moveNumber) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.moveNumber = moveNumber;
	}
	
	public int getFromX() {
		return fromX;
	}
	
	public int getFromY() {
		return fromY;
	}
	
	public int getToX() {
		return toX;
	}
	
	public int getToY() {
		return toY;
	}
	
	public int getMoveNumber() {
		return moveNumber;
	}
}
