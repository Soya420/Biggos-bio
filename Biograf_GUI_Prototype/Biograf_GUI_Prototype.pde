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

  String[] tickets = new String[20];
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
  //switch (keyCode) {
  //case RIGHT:
  //  for (Seat s : hall.selected) {
  //    hall.seats[s.colNum][s.rowNum].status = 0;
  //    hall.seats[s.colNum+1][s.rowNum].status = 1;
  //  }
  //  break;
  //case LEFT:
  //  for (int i = 0; i < hall.cols; i++) for (int j = 0; j < hall.rows; j++) {
  //    if (hall.seats[i][j].status == 1) {
  //      hall.seats[i][j].status = 0;
  //      hall.seats[i-1][j].status = 1;
  //    }
  //  }
  //  break;
  //case UP:
  //  for (int i = 0; i < hall.cols; i++) for (int j = 0; j < hall.rows; j++) {
  //    if (hall.seats[i][j].status == 1) {
  //      hall.seats[i][j].status = 0;
  //      hall.seats[i][j-1].status = 1;
  //    }
  //  }
  //  break;
  //case DOWN:
  //  for (int i = 0; i < hall.cols; i++) for (int j = hall.rows-1; j >= 0; j--) {
  //    if (hall.seats[i][j].status == 1) {
  //      hall.seats[i][j].status = 0;
  //      hall.seats[i][j+1].status = 1;
  //    }
  //  }
  //  break;
  //}



  switch (keyCode) {
  case RIGHT:
    for (int i = hall.cols-1; i >= 0; i--) for (int j = 0; j < hall.rows; j++) {
      if (hall.seats[i][j].status == 1) {
        hall.seats[i][j].status = 0;
        if (hall.seats[i+1][j].status < 2) {
          hall.seats[i+1][j].status = 1;
        }
      }
    }
    break;
  case LEFT:
    for (int i = 0; i < hall.cols; i++) for (int j = 0; j < hall.rows; j++) {
      if (hall.seats[i][j].status == 1) {
        hall.seats[i][j].status = 0;
        hall.seats[i-1][j].status = 1;
      }
    }
    break;
  case UP:
    for (int i = 0; i < hall.cols; i++) for (int j = 0; j < hall.rows; j++) {
      if (hall.seats[i][j].status == 1) {
        hall.seats[i][j].status = 0;
        hall.seats[i][j-1].status = 1;
      }
    }
    break;
  case DOWN:
    for (int i = 0; i < hall.cols; i++) for (int j = hall.rows-1; j >= 0; j--) {
      if (hall.seats[i][j].status == 1) {
        hall.seats[i][j].status = 0;
        hall.seats[i][j+1].status = 1;
      }
    }
    break;
  }
}
