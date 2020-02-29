package bioprojekt.ui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import bioprojekt.Main;
import bioprojekt.database.SQLHandler;

@SuppressWarnings("serial")
public class Applet extends JFrame {
	
	public static final Dimension DEFAULT_PAGE_DIMENSION = new Dimension(400, 400);
	
	// Uses a JTabbedPane as container, to enable multiple menus.
	private JTabbedPane contentPane;
	private SQLHandler sqlh;
	
	public Applet() {
		super("Server / Admin Tool");
		
		sqlh = new SQLHandler();
		
		// Sets default look and feel for every component to fit OS look and feel.
		// These exceptions are the only that would be thrown outside the GUI implemented console
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				Main.exit();
			}
		});
		
		setLocation(0, 0);
		
		contentPane = new JTabbedPane();
		
		setContentPane(contentPane);
		
		// Adds null as text because custom tabs later
		contentPane.addTab(null, new ManageMenu());
		
		// Custom tabs
		contentPane.setTabComponentAt(0, getTabLabel("Manage menu"));
		
		pack();
		setVisible(true);
	}
	
	// Returns a tab label in default size with provided text
	public static JComponent getTabLabel(String tabText) {
		JLabel tabLabel = new JLabel(tabText, SwingConstants.CENTER);
		tabLabel.setPreferredSize(new Dimension(80, 12));
		return tabLabel;
	}
	public SQLHandler getSQLHandler() {
		return sqlh;
	}
}