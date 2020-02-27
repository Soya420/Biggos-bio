package bioprojekt.ui;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import bioprojekt.Main;
import bioprojekt.database.Cinema;
import bioprojekt.database.Hall;


@SuppressWarnings("serial")
public class ManageMenu extends JPanel {

	public ManageMenu() {
		setPreferredSize(Applet.DEFAULT_PAGE_DIMENSION);

		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		AddCinemaPanel addCinemaPanel = new AddCinemaPanel();
		layout.putConstraint(SpringLayout.NORTH, addCinemaPanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, addCinemaPanel, Applet.DEFAULT_PAGE_DIMENSION.height/2-5, SpringLayout.NORTH, addCinemaPanel);
		layout.putConstraint(SpringLayout.WEST, addCinemaPanel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, addCinemaPanel, -Applet.DEFAULT_PAGE_DIMENSION.width/2-5, SpringLayout.EAST, this);
		add(addCinemaPanel);

		RemoveCinemaPanel removeCinemaPanel = new RemoveCinemaPanel();
		layout.putConstraint(SpringLayout.NORTH, removeCinemaPanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, removeCinemaPanel, Applet.DEFAULT_PAGE_DIMENSION.height/2-5, SpringLayout.NORTH, removeCinemaPanel);
		layout.putConstraint(SpringLayout.WEST, removeCinemaPanel, 10, SpringLayout.EAST, addCinemaPanel);
		layout.putConstraint(SpringLayout.EAST, removeCinemaPanel, -5, SpringLayout.EAST, this);
		add(removeCinemaPanel);

		AddHallPanel addHallPanel = new AddHallPanel();
		layout.putConstraint(SpringLayout.NORTH, addHallPanel, Applet.DEFAULT_PAGE_DIMENSION.height/2+5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, addHallPanel, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, addHallPanel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, addHallPanel, -Applet.DEFAULT_PAGE_DIMENSION.width/2-5, SpringLayout.EAST, this);
		add(addHallPanel);

		RemoveHallPanel removeHallPanel = new RemoveHallPanel();
		layout.putConstraint(SpringLayout.NORTH, removeHallPanel, Applet.DEFAULT_PAGE_DIMENSION.height/2+5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, removeHallPanel, -5, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, removeHallPanel, 10, SpringLayout.EAST, addHallPanel);
		layout.putConstraint(SpringLayout.EAST, removeHallPanel, -5, SpringLayout.EAST, this);
		add(removeHallPanel);
	}

	class CinemaComboBox extends JComboBox<Cinema> {

		public CinemaComboBox() {

			addPopupMenuListener(new PopupMenuListener() {

				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					try {
						updateModel(Main.applet.getSQLHandler().getAllCinemas());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {}
			});

		}
		private void updateModel(Vector<Cinema> cinemas) {
			setModel(new DefaultComboBoxModel<>(cinemas));
		}
	}

	class HallsComboBox extends JComboBox<Hall> {

		private void updateModel(Vector<Hall> halls) {
			setModel(new DefaultComboBoxModel<>(halls));
		}


	}

	@SuppressWarnings("serial")
	class AddCinemaPanel extends JPanel {
		private JLabel cinemaName;
		private JTextField addCinemaField;
		private JButton addButton;


		public AddCinemaPanel() {
			SpringLayout layout = new SpringLayout();
			setLayout(layout);
			setBorder(BorderFactory.createTitledBorder("Add cinema"));

			cinemaName = new JLabel("Cinema name");
			layout.putConstraint(SpringLayout.NORTH, cinemaName, 20, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.SOUTH, cinemaName, 24, SpringLayout.NORTH, cinemaName);
			layout.putConstraint(SpringLayout.WEST, cinemaName, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, cinemaName, 75, SpringLayout.WEST, this);
			add(cinemaName);

			addCinemaField = new JTextField();
			layout.putConstraint(SpringLayout.NORTH, addCinemaField, 0, SpringLayout.SOUTH, cinemaName);
			layout.putConstraint(SpringLayout.SOUTH, addCinemaField, 24, SpringLayout.NORTH, addCinemaField);
			layout.putConstraint(SpringLayout.WEST, addCinemaField, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, addCinemaField, 150, SpringLayout.WEST, this);
			add(addCinemaField);

			addButton = new JButton("Add cinema");
			layout.putConstraint(SpringLayout.NORTH, addButton, 20, SpringLayout.SOUTH, addCinemaField);
			layout.putConstraint(SpringLayout.SOUTH, addButton, 24, SpringLayout.NORTH, addButton);
			layout.putConstraint(SpringLayout.WEST, addButton, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, addButton, 100, SpringLayout.WEST, this);

			addButton.addActionListener(e -> {
				try {
					Main.applet.getSQLHandler().addCinema(new Cinema(addCinemaField.getText()));
					addCinemaField.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			});

			add(addButton);
		}

	}

	class RemoveCinemaPanel extends JPanel {
		private JLabel cinemaName;
		private JButton removeButton;
		private CinemaComboBox ccb;

		public RemoveCinemaPanel() {
			SpringLayout layout = new SpringLayout();
			setLayout(layout);
			setBorder(BorderFactory.createTitledBorder("Remove cinema"));

			cinemaName = new JLabel("Cinema name");
			layout.putConstraint(SpringLayout.NORTH, cinemaName, 20, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.SOUTH, cinemaName, 24, SpringLayout.NORTH, cinemaName);
			layout.putConstraint(SpringLayout.WEST, cinemaName, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, cinemaName, 75, SpringLayout.WEST, this);
			add(cinemaName);

			ccb = new CinemaComboBox();
			layout.putConstraint(SpringLayout.NORTH, ccb, 0, SpringLayout.SOUTH, cinemaName);
			layout.putConstraint(SpringLayout.SOUTH, ccb, 24, SpringLayout.NORTH, ccb);
			layout.putConstraint(SpringLayout.WEST, ccb, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, ccb, 150, SpringLayout.WEST, this);
			add(ccb);

			removeButton = new JButton("Remove");
			layout.putConstraint(SpringLayout.NORTH, removeButton, 20, SpringLayout.SOUTH, ccb);
			layout.putConstraint(SpringLayout.SOUTH, removeButton, 24, SpringLayout.NORTH, removeButton);
			layout.putConstraint(SpringLayout.WEST, removeButton, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, removeButton, 100, SpringLayout.WEST, this);

			removeButton.addActionListener(e -> {
				try {
					Main.applet.getSQLHandler().removeAllHallsFromCinema((Cinema) ccb.getSelectedItem());
					Main.applet.getSQLHandler().removeCinema((Cinema) ccb.getSelectedItem());
					ccb.setSelectedItem(null);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			});

			add(removeButton);
		}

	}

	class AddHallPanel extends JPanel {
		private JLabel cinemaName, rowsLabel, coloumnsLabel, filmLabel;
		private JTextField rowsField, coloumnsField, filmField;
		private CinemaComboBox ccb;
		private JButton addButton;
		private int hallRows, hallColoumns;
		private Cinema selected;

		public AddHallPanel() {
			SpringLayout layout = new SpringLayout();
			setLayout(layout);
			setBorder(BorderFactory.createTitledBorder("Add hall"));

			cinemaName = new JLabel("Cinema name");
			layout.putConstraint(SpringLayout.NORTH, cinemaName, 5, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.SOUTH, cinemaName, 24, SpringLayout.NORTH, cinemaName);
			layout.putConstraint(SpringLayout.WEST, cinemaName, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, cinemaName, 75, SpringLayout.WEST, this);
			add(cinemaName);

			ccb = new CinemaComboBox();
			layout.putConstraint(SpringLayout.NORTH, ccb, 0, SpringLayout.SOUTH, cinemaName);
			layout.putConstraint(SpringLayout.SOUTH, ccb, 24, SpringLayout.NORTH, ccb);
			layout.putConstraint(SpringLayout.WEST, ccb, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, ccb, 150, SpringLayout.WEST, this);
			try {
				ccb.setSelectedIndex(0);
			} catch (IllegalArgumentException e) {
				ccb.setSelectedItem(null);
			}
			add(ccb);


			rowsLabel = new JLabel("Rows#:");
			layout.putConstraint(SpringLayout.NORTH, rowsLabel, 5, SpringLayout.SOUTH, ccb);
			layout.putConstraint(SpringLayout.SOUTH, rowsLabel, 24, SpringLayout.NORTH, rowsLabel);
			layout.putConstraint(SpringLayout.WEST, rowsLabel, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, rowsLabel, 75, SpringLayout.WEST, this);
			add(rowsLabel);

			coloumnsLabel = new JLabel("Coloumns#:");
			layout.putConstraint(SpringLayout.NORTH, coloumnsLabel, 5, SpringLayout.SOUTH, rowsLabel);
			layout.putConstraint(SpringLayout.SOUTH, coloumnsLabel, 24, SpringLayout.NORTH, coloumnsLabel);
			layout.putConstraint(SpringLayout.WEST, coloumnsLabel, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, coloumnsLabel, 75, SpringLayout.WEST, this);
			add(coloumnsLabel);

			filmLabel = new JLabel("Film:");
			layout.putConstraint(SpringLayout.NORTH, filmLabel, 5, SpringLayout.SOUTH, coloumnsLabel);
			layout.putConstraint(SpringLayout.SOUTH, filmLabel, 24, SpringLayout.NORTH, filmLabel);
			layout.putConstraint(SpringLayout.WEST, filmLabel, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, filmLabel, 35, SpringLayout.WEST, this);
			add(filmLabel);

			rowsField = new JTextField();
			layout.putConstraint(SpringLayout.NORTH, rowsField, 5, SpringLayout.SOUTH, ccb);
			layout.putConstraint(SpringLayout.SOUTH, rowsField, 24, SpringLayout.NORTH, rowsField);
			layout.putConstraint(SpringLayout.WEST, rowsField, 5, SpringLayout.EAST, rowsLabel);
			layout.putConstraint(SpringLayout.EAST, rowsField, 150, SpringLayout.WEST, this);
			add(rowsField);

			coloumnsField = new JTextField();
			layout.putConstraint(SpringLayout.NORTH, coloumnsField, 5, SpringLayout.SOUTH, rowsField);
			layout.putConstraint(SpringLayout.SOUTH, coloumnsField, 24, SpringLayout.NORTH, coloumnsField);
			layout.putConstraint(SpringLayout.WEST, coloumnsField, 5, SpringLayout.EAST, coloumnsLabel);
			layout.putConstraint(SpringLayout.EAST, coloumnsField, 150, SpringLayout.WEST, this);
			add(coloumnsField);

			filmField = new JTextField();
			layout.putConstraint(SpringLayout.NORTH, filmField, 5, SpringLayout.SOUTH, coloumnsField);
			layout.putConstraint(SpringLayout.SOUTH, filmField, 24, SpringLayout.NORTH, filmField);
			layout.putConstraint(SpringLayout.WEST, filmField, 5, SpringLayout.EAST, filmLabel);
			layout.putConstraint(SpringLayout.EAST, filmField, 150, SpringLayout.WEST, this);
			add(filmField);

			addButton = new JButton("Add hall");
			layout.putConstraint(SpringLayout.NORTH, addButton, 5, SpringLayout.SOUTH, filmLabel);
			layout.putConstraint(SpringLayout.SOUTH, addButton, 24, SpringLayout.NORTH, addButton);
			layout.putConstraint(SpringLayout.WEST, addButton, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, addButton, 100, SpringLayout.WEST, this);

			addButton.addActionListener(e -> {
				if (isInteger(rowsField.getText()) && isInteger(coloumnsField.getText()) && (Cinema) ccb.getSelectedItem() != null)  {
					System.out.println("integer");
					try {
						hallRows = Integer.parseInt(rowsField.getText());
						hallColoumns = Integer.parseInt(coloumnsField.getText());

						selected = (Cinema) ccb.getSelectedItem();
						String filmNavn = filmField.getText();
						Main.applet.getSQLHandler().addHall(new Hall(hallRows, hallColoumns, selected.id, filmNavn));
						rowsField.setText("");
						coloumnsField.setText("");
						filmField.setText("");
						ccb.setSelectedItem(null);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("Error wrong output");
				}
			});

			add(addButton);
		}

		public boolean isInteger(String s) {
			if (s.length() == 0) return false;

			//skal være positiv
			if (s.charAt(0) == '-') return false;

			try {
				if (Integer.parseInt(s) == 0) return false;
			} 
			catch(NumberFormatException e) { 
				return false;
			} 
			catch(NullPointerException e) {
				return false;
			}
			return true;
		}

	}

	class RemoveHallPanel extends JPanel {
		private JLabel cinemaName, hallNumber;
		private CinemaComboBox ccb;
		private HallsComboBox hccb;
		private JButton removeButton;


		public RemoveHallPanel() {
			SpringLayout layout = new SpringLayout();
			setLayout(layout);
			setBorder(BorderFactory.createTitledBorder("Remove hall"));

			cinemaName = new JLabel("Cinema name");
			layout.putConstraint(SpringLayout.NORTH, cinemaName, 5, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.SOUTH, cinemaName, 24, SpringLayout.NORTH, cinemaName);
			layout.putConstraint(SpringLayout.WEST, cinemaName, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, cinemaName, 75, SpringLayout.WEST, this);
			add(cinemaName);

			ccb = new CinemaComboBox();
			layout.putConstraint(SpringLayout.NORTH, ccb, 0, SpringLayout.SOUTH, cinemaName);
			layout.putConstraint(SpringLayout.SOUTH, ccb, 24, SpringLayout.NORTH, ccb);
			layout.putConstraint(SpringLayout.WEST, ccb, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, ccb, 150, SpringLayout.WEST, this);
			add(ccb);

			hallNumber = new JLabel("Hall number");
			layout.putConstraint(SpringLayout.NORTH, hallNumber, 5, SpringLayout.SOUTH, ccb);
			layout.putConstraint(SpringLayout.SOUTH, hallNumber, 24, SpringLayout.NORTH, hallNumber);
			layout.putConstraint(SpringLayout.WEST, hallNumber, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, hallNumber, 150, SpringLayout.WEST, this);
			add(hallNumber);

			hccb = new HallsComboBox();
			layout.putConstraint(SpringLayout.NORTH, hccb, 0, SpringLayout.SOUTH, hallNumber);
			layout.putConstraint(SpringLayout.SOUTH, hccb, 24, SpringLayout.NORTH, hccb);
			layout.putConstraint(SpringLayout.WEST, hccb, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, hccb, 150, SpringLayout.WEST, this);

			ccb.addPopupMenuListener(new PopupMenuListener() {

				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				}

				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					try {
						if (ccb.getSelectedItem() != null) {

							hccb.updateModel(Main.applet.getSQLHandler().getHallsFromCinema((Cinema) ccb.getSelectedItem()));


						} else {
							hccb.updateModel(Main.applet.getSQLHandler().getAllHalls());
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
				}
			});

			add(hccb);

			removeButton = new JButton("Remove hall");
			layout.putConstraint(SpringLayout.NORTH, removeButton, 10, SpringLayout.SOUTH, hccb);
			layout.putConstraint(SpringLayout.SOUTH, removeButton, 24, SpringLayout.NORTH, removeButton);
			layout.putConstraint(SpringLayout.WEST, removeButton, 5, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, removeButton, 100, SpringLayout.WEST, this);

			removeButton.addActionListener(e -> {
				try {
					Main.applet.getSQLHandler().removeHall((Hall) hccb.getSelectedItem());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				hccb.setSelectedItem(null);
			});
			add(removeButton);
		}

	}

}