package bioprojekt.database;

public class Seat {
	
	public static final int NO_ID = -1;
	
	public int id, rNumber, cNumber, rID, hID;
	
	public Seat(int id, int rNumber, int cNumber, int rID, int hID) {
		this.id = id;
		this.rNumber = rNumber;
		this.cNumber = cNumber;
		this.rID = rID;
		this.hID = hID;
	}
	
	public Seat(int rNumber, int cNumber, int rID, int hID) {
		id = NO_ID;
		this.rNumber = rNumber;
		this.cNumber = cNumber;
		this.rID = rID;
		this.hID = hID;
	}
	
}
