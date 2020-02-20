package bioprojekt.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


@SuppressWarnings("serial")
public class ManageMenu extends JPanel {

	public ManageMenu() {
		setPreferredSize(Applet.DEFAULT_PAGE_DIMENSION);
		
		int height = Applet.DEFAULT_PAGE_DIMENSION.height;
		int width = Applet.DEFAULT_PAGE_DIMENSION.width;
		
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		AddCinemaPanel addCinemaPanel = new AddCinemaPanel();
		layout.putConstraint(SpringLayout.NORTH, addCinemaPanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, addCinemaPanel, height/2-5, SpringLayout.NORTH, addCinemaPanel);
		layout.putConstraint(SpringLayout.WEST, addCinemaPanel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, addCinemaPanel, -width/2-5, SpringLayout.EAST, this);
		add(addCinemaPanel);
		
		RemoveCinemaPanel removeCinemaPanel = new RemoveCinemaPanel();
		layout.putConstraint(SpringLayout.NORTH, removeCinemaPanel, height/2+5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, removeCinemaPanel, height/2-5, SpringLayout.NORTH, removeCinemaPanel);
		layout.putConstraint(SpringLayout.WEST, removeCinemaPanel, width+5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, removeCinemaPanel, 5, SpringLayout.EAST, this);
		add(removeCinemaPanel);
		
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
			add(addButton);
		}
		
	}
	
	class RemoveCinemaPanel extends JPanel {
		private JLabel cinemaName;
		private JTextField addCinemaField;
		private JButton addButton;
		
		
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
	
	class AddHallPanel extends JPanel {
		private JLabel cinemaName;
		private JTextField addCinemaField;
		private JButton addButton;
		
		
		public AddHallPanel() {
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
			add(addButton);
		}
		
	}
	
}