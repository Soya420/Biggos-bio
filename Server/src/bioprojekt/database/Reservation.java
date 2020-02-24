package bioprojekt.database;

public class Reservation {
	
	public static final int NO_ID = -1;
	
	public int id, phoneNumber;
	public String password;
	
	public Reservation(int id, int phoneNumber, String password) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public Reservation(int phoneNumber, String password) {
		id = NO_ID;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public String toString() {
		return "" + phoneNumber;
	}
}
