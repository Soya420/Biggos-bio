import g4p_controls.*;
import java.awt.*;

Hall hall;
ServerHandler sh;
boolean updated;
boolean server = true;

public void setup() {
  size(1280, 720, JAVA2D);
  createGUI();
  customGUI();
  if (server) {
    sh = new ServerHandler();
  }
  rectMode(CENTER);
  textAlign(CENTER, TOP);
  textSize(35);
  hall = new Hall(13, 11);
  hall.createSeats();
}

public void draw() {
  background(20, 29, 38);
  hall.display();
  updateGUI();
  println("f: "+frameCount);
}

void keyPressed() {
  if (key == CODED) {
    switch (keyCode) {
    case RIGHT:
      hall.right();
      break;
    case LEFT:
      hall.left();
      break;
    case DOWN:
      hall.down();
      break;
    case UP:
      hall.up();
      break;
    }
  }
}

void updateGUI() {
  if (biograf_dropList.hasFocus() && !updated) {
    if (server) sh.getData("cinemas");
    updated = true;
  }
  if (!biograf_dropList.hasFocus()) {
    updated = false;
  }
}

boolean isInteger(String s) {
  //skal være positiv
  if (s.charAt(0) == '-') return false;

  try { 
    Integer.parseInt(s);
  } 
  catch(NumberFormatException e) { 
    return false;
  } 
  catch(NullPointerException e) {
    return false;
  }
  return true;
}

public void customGUI() {
  logind_window.setLocation(710, 290);
  afbestil_window.setLocation(710, 290);
  nybruger_window.setLocation(710, 290);

  logind_window.setVisible(false);
  afbestil_window.setVisible(false);
  nybruger_window.setVisible(false);

  String[] tickets = new String[12];
  for (int i = 0; i < tickets.length; i++) tickets[i] = (i+1)+"";

  billetter_dropList.setFont(new Font("Ariel", Font.PLAIN, 18));
  billetter_dropList.setItems(tickets, 2);
  biograf_dropList.setFont(new Font("Ariel", Font.PLAIN, 18));
  reserver_button.setFont(new Font("Ariel", Font.PLAIN, 24));
  reserver_label.setFont(new Font("Ariel", Font.PLAIN, 18));
  film_dropList.setFont(new Font("Ariel", Font.PLAIN, 18));
  afbestil_button.setFont(new Font("Ariel", Font.PLAIN, 24));
  afbestil_label.setFont(new Font("Ariel", Font.PLAIN, 21));
  afbetil_final_button.setFont(new Font("Ariel", Font.PLAIN, 18));
  tlf_afbestil_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  adg_afbestil_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  tlf_logind_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  adg_logind_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  nybruger_button.setFont(new Font("Ariel", Font.PLAIN, 15));
  logind_button.setFont(new Font("Ariel", Font.PLAIN, 24));
  logind_label.setFont(new Font("Ariel", Font.PLAIN, 15));
  nybruger_label.setFont(new Font("Ariel", Font.PLAIN, 18));
  nybruger_final_label.setFont(new Font("Ariel", Font.PLAIN, 15));
  nybruger_final_button.setFont(new Font("Ariel", Font.PLAIN, 18));
  tlf_nybruger_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  adg1_nybruger_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  adg2_nybruger_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
}
