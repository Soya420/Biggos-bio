import g4p_controls.*;
import java.awt.*;


public void setup(){
  size(1280, 720, JAVA2D);
  createGUI();
  customGUI();
  // Place your setup code here
  
}

public void draw(){
  background(20, 29, 38);
}

// Use this method to add additional statements
// to customise the GUI controls
public void customGUI(){
  logind_window.setLocation(710, 290);
  afbestil_window.setLocation(710, 290);
  nyprofil_window.setLocation(710, 290);
  
  logind_window.setVisible(false);
  afbestil_window.setVisible(false);
  nyprofil_window.setVisible(false);
  
  
}
