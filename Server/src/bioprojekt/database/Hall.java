package bioprojekt.database;

// Class for hall in the database
public class Hall {
	
public static final int NO_ID = -1;
	
	public int id, rows, coloumns, cinemaID;
	public String movie;
	
	public Hall(int id, int rows, int coloumns, int cinemaID, String movie) {
		this.id = id;
		this.rows = rows;
		this.coloumns = coloumns;
		this.cinemaID = cinemaID;
		this.movie = movie;
	}
	
	public Hall(int rows, int coloumns, int cinemaID, String movie) {
		id = NO_ID;
		this.rows = rows;
		this.coloumns = coloumns;
		this.cinemaID = cinemaID;
		this.movie = movie;
	}
	
	@Override
	public String toString() {
		return "" + id;
	}
	
}
