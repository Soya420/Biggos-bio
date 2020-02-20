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
		layout.putConstraint(SpringLayout.SOUTH, addCinemaPanel, 200, SpringLayout.NORTH, addCinemaPanel);
		layout.putConstraint(SpringLayout.WEST, addCinemaPanel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, addCinemaPanel, -width/2-5, SpringLayout.EAST, this);
		add(addCinemaPanel);
		
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
			
			addButton = new JButton();
			
		}
		
	}
	
	
	
}