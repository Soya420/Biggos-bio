package bioprojekt.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import bioprojekt.database.Cinema;
import bioprojekt.database.Hall;
import bioprojekt.database.Reservation;
import bioprojekt.database.Seat;

public final class ResultSetHelper {

	private ResultSetHelper() {}
	
	public static Cinema toCinema (ResultSet rs) throws SQLException {
		rs.next();
		return new Cinema(rs.getInt(1), rs.getString(2));
	}
	
	public static Vector<Cinema> toCinemas(ResultSet rs) throws SQLException{
		Vector<Cinema> cinemas = new Vector<>();
		while (rs.next()) {
			cinemas.add(new Cinema(rs.getInt(1), rs.getString(2)));
		}
		return cinemas;
	}
	
	public static Vector<Hall> toHalls(ResultSet rs) throws SQLException{
		Vector<Hall> halls = new Vector<>();
		while (rs.next()) {
			halls.add(new Hall(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
		}
		return halls;
	}
	
	public static Vector<Reservation> toReservations(ResultSet rs) throws SQLException{
		Vector<Reservation> reservations = new Vector<>();
		while (rs.next()) {
			reservations.add(new Reservation(rs.getInt(1), rs.getInt(2), rs.getString(3)));
		}
		return reservations;
	}
	
	public static Vector<Seat> toSeats(ResultSet rs) throws SQLException{
		Vector<Seat> seats = new Vector<>();
		while (rs.next()) {
			seats.add(new Seat(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
		}
		return seats;
	}
	
}
