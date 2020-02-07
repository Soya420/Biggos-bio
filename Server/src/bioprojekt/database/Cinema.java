package bioprojekt.database;

public class Cinema {
	
	public static final int NO_ID = -1;
	
	public int id;
	public String name;
	
	public Cinema(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Cinema(String name) {
		id = NO_ID;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
