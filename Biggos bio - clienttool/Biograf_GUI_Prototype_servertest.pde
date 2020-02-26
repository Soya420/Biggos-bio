import g4p_controls.*;
import java.awt.*;

Hall currentHall;
Hall[] allHalls;
Cinema[] allCinemas;
ServerHandler sh;
boolean cinemaUpdated, movieUpdated;

public void setup() {
  size(1280, 720, JAVA2D);
  createGUI();
  customGUI();
  sh = new ServerHandler();

  rectMode(CENTER);
  textAlign(CENTER, TOP);
  textSize(35);
}

public void draw() {
  background(20, 29, 38);
  if (currentHall != null) currentHall.display();
  updateGUI();
}


void updateGUI() {
  //biograf droplist
  if (biograf_dropList.hasFocus() && !cinemaUpdated) {
    sh.getData("cinemas");
    cinemaUpdated = true;
  }
  if (!biograf_dropList.hasFocus()) {
    cinemaUpdated = false;
  }

  //film droplist
  if (film_dropList.hasFocus() && !movieUpdated) {

    if (allCinemas == null) {

      String[] error = {"Ingen valgt biograf"};
      film_dropList.setItems(error, film_dropList.getSelectedIndex());
    } else sh.getData("halls%"+(allCinemas[biograf_dropList.getSelectedIndex()].ID)+"%"+allCinemas[biograf_dropList.getSelectedIndex()].name);
    movieUpdated = true;
    
    //når man har valgt noget fra droplisten
  } else if (!film_dropList.hasFocus()) {
    if (movieUpdated && allCinemas != null) {
      sh.getData("seats%"+allHalls[film_dropList.getSelectedIndex()].ID);
    }
    movieUpdated = false;
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


String[] splitString(String[] s) {
  //splitter en for at se hvor mange værdier der er
  String[] t = s[0].split(",");
  String[] split = new String[s.length*t.length];


  for (int i = 0; i < s.length; i++) {
    String[] temp = s[i].split(",");

    for (int j = 0; j < temp.length; j++) {
      //lægger alle String[] sammen til én
      split[(i*t.length)+j] = temp[j];
    }
  }

  return split;
}


void keyPressed() {
  if (key == CODED) {
    switch (keyCode) {
    case RIGHT:
      currentHall.right();
      break;
    case LEFT:
      currentHall.left();
      break;
    case DOWN:
      currentHall.down();
      break;
    case UP:
      currentHall.up();
      break;
    }
  }
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
  afbestil_final_label.setFont(new Font("Ariel", Font.PLAIN, 15));
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
