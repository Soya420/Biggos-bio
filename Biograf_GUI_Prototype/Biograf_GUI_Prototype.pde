import g4p_controls.*;
import java.awt.*;
Hall hall;

public void setup() {
  size(1280, 720, JAVA2D);
  createGUI();
  customGUI();
  // Place your setup code here
  rectMode(CENTER);
  textAlign(CENTER, TOP);
  textSize(35);
  hall = new Hall(12, 10);
  hall.createSeats();
}

public void draw() {
  background(20, 29, 38);
  hall.display();
}

// Use this method to add additional statements
// to customise the GUI controls
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
  reserver_button.setFont(new Font("Ariel", Font.PLAIN, 24));
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
  nybruger_label.setFont(new Font("Ariel", Font.PLAIN, 18)); 
  nybruger_final_button.setFont(new Font("Ariel", Font.PLAIN, 18));
  tlf_nybruger_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  adg1_nybruger_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
  adg2_nybruger_textfield.setFont(new Font("Ariel", Font.PLAIN, 18));
}

void keyPressed() {
  switch (keyCode) {
  case RIGHT:
    for (int i = hall.selected.length-1; i >= 0; i--) {
      if (hall.selected[i].colNum + 1 < hall.cols) {
        hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum].status = 0;
        hall.seats[hall.selected[i].colNum + 1][hall.selected[i].rowNum].status = 1;
        hall.selected[i] = hall.seats[hall.selected[i].colNum + 1][hall.selected[i].rowNum];
      } else break;
    }
    break;
  case LEFT:
    for (int i = 0; i < hall.selected.length; i++) {
      if (hall.selected[i].colNum - 1 >= 0) {
        hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum].status = 0;
        hall.seats[hall.selected[i].colNum - 1][hall.selected[i].rowNum].status = 1;
        hall.selected[i] = hall.seats[hall.selected[i].colNum - 1][hall.selected[i].rowNum];
      } else break;
    }
    break;
  case UP:
    for (int i = hall.selected.length-1; i >= 0; i--) {
      if (hall.selected[i].rowNum - 1 >= 0) {
        hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum].status = 0;
        hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum - 1].status = 1;
        hall.selected[i] = hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum - 1];
      } else break;
    }
    break;
  case DOWN:
    for (int i = 0; i < hall.selected.length; i++) {
      if (hall.selected[i].rowNum + 1 < hall.rows) {
        hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum].status = 0;
        hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum + 1].status = 1;
        hall.selected[i] = hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum + 1];
      } else break;
    }
    break;
  }
}
