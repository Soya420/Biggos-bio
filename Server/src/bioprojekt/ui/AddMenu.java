package bioprojekt.ui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class AddMenu extends JPanel {

	public AddMenu() {
		SpringLayout layout = new SpringLayout();
		setPreferredSize(Applet.DEFAULT_PAGE_DIMENSION);
		setLayout(layout);
		
		AddCinemaPanel addCinemaPanel = new AddCinemaPanel();
		layout.putConstraint(SpringLayout.NORTH, addCinemaPanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, addCinemaPanel, 400, SpringLayout.NORTH, addCinemaPanel);
		layout.putConstraint(SpringLayout.WEST, addCinemaPanel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, addCinemaPanel, -5, SpringLayout.EAST, this);
		
	}
	
	class AddCinemaPanel extends JPanel {
		private JLabel cinemaName;
		
		
		AddCinemaPanel() {
			setBorder(BorderFactory.createTitledBorder("Add cinema"));
			
			
		}
		
	}
	
}