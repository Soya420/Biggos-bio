package bioprojekt.database;

public class Cinema {
	
	public static final int NO_ID = -1;
	
	public int id, layout_id;
	public String name;
	
	public Cinema(int id, String name, int layout_id) {
		this.id = id;
		this.layout_id = layout_id;
		this.name = name;
	}
	
	public Cinema(String name, int layout_id) {
		id = NO_ID;
		this.layout_id = layout_id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " - " + layout_id;
	}
}
