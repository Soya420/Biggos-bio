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
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			});
			
			add(removeButton);
		}
		
	}
	
	class AddHallPanel extends JPanel {
		private JLabel cinemaName;
		private JTextField addCinemaField;
		private JButton addButton;
		
		
		public AddHallPanel() {
			SpringLayout layout = new SpringLayout();
			setLayout(layout);
			setBorder(BorderFactory.createTitledBorder("Add hall"));
			
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
			add(addButton);
		}
		
	}
	
	class RemoveHallPanel extends JPanel {
		private JLabel cinemaName;
		private JTextField addCinemaField;
		private JButton addButton;
		
		
		public RemoveHallPanel() {
			SpringLayout layout = new SpringLayout();
			setLayout(layout);
			setBorder(BorderFactory.createTitledBorder("Remove hall"));
			
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
			add(addButton);
		}
		
	}
	
}