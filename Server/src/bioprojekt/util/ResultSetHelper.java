package bioprojekt.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import bioprojekt.database.Cinema;
import bioprojekt.database.Hall;

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

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		
		if(rs == null) {
			return new DefaultTableModel();
		}
		
		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();

		int columnCount = metaData.getColumnCount();

		// sql indexing starts at 1
		for (int column = 1; column <= columnCount; column++) {
			System.out.print("hej");
			columnNames.add(metaData.getColumnLabel(column));
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		// loop until no more rows
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();

			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}

			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);
	}
}
