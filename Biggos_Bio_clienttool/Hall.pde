class Hall { //<>// //<>//
  int ID;
  int cols, rows;
  String movie;
  Seat[][] seats;
  Seat[] selected;

  Hall(String movie_, int ID_) {
    movie = movie_;
    ID = ID_;
  }

  Hall(int c, int r) {
    cols = c;
    rows = r;
    seats = new Seat[cols][rows];
  }

  void createSeats() {
    int wid = 620/cols;
    int hei = 620/rows;
    for (int i = 0; i < cols; i++) for (int j = 0; j < rows; j++) {
      seats[i][j] = new Seat(i, j, wid, hei);
    }
  }

  void display() {
    noStroke();
    for (int i = 0; i < cols; i++) for (int j = 0; j < rows; j++) {
      seats[i][j].display();
    }
    //outliners
    stroke(255);
    strokeWeight(1);
    line(280, 0, 280, height);
    line(1000, 0, 1000, height);

    //lærred
    strokeWeight(6);
    line(380, 12, 900, 12);
    fill(255);
    text("LÆRRED", 640, 20);

    //label
    if (selected != null) {
      if (selected.length == 1) reserver_label.setText("Række "+(selected[0].rowNum+1)+"\nSæde "+(selected[0].colNum+1));
      else reserver_label.setText("Række "+(selected[0].rowNum+1)+"\nSæde "+(selected[0].colNum+1)+" - "+(selected[selected.length-1].colNum+1));
    } else {
      reserver_label.setText("Ingen ledige pladser ved siden af hinanden");
    }
  }



  void findEmptySeats() {

    boolean occupied = true;
    int x = -1, y = 0;

    while (occupied) {
      occupied = false;


      if (x < cols-billetter_dropList.getSelectedIndex()-1 && y < rows-1) {
        x++;
      } else {
        x = 0;
        if (y < rows-1) {
          y++;
        } else {
          occupied = true;
          break;
        }
      }

      for (int i = 0; i < billetter_dropList.getSelectedIndex()+1; i++) {
        if (seats[x+i][y].status > 1) {
          occupied = true;
          break;
        }
      }
    }

    if (!occupied) {
      selected = new Seat[billetter_dropList.getSelectedIndex()+1];

      for (int i = 0; i < billetter_dropList.getSelectedIndex()+1; i++) {
        seats[x+i][y].status = 1;
        selected[i] = seats[x+i][y];
      }
    } else {
      reserver_label.setText("Ingen ledige pladser");
    }
  }




  void right() {
    boolean blocked = true;
    int move = 0;
    while (blocked && selected[selected.length-1].colNum + move < cols-1) {
      blocked = false;
      move++;

      for (int i = selected.length-1; i >= 0; i--) {
        if (seats[selected[i].colNum + move][selected[i].rowNum].status > 1) {
          blocked = true;
          break;
        }
      }
    }
    if (!blocked) {
      for (int i = selected.length-1; i >= 0; i--) {
        seats[selected[i].colNum][selected[i].rowNum].status = 0;
        seats[selected[i].colNum + move][selected[i].rowNum].status = 1;
        selected[i] = seats[selected[i].colNum + move][selected[i].rowNum];
      }
    }
  }

  void left() {
    boolean blocked = true;
    int move = 0;
    while (blocked && selected[0].colNum - move > 0) {
      blocked = false;
      move++;

      for (int i = 0; i < selected.length; i++) {
        if (seats[selected[i].colNum - move][selected[i].rowNum].status > 1) {
          blocked = true;
          break;
        }
      }
    }
    if (!blocked) {
      for (int i = 0; i < selected.length; i++) {
        seats[selected[i].colNum][selected[i].rowNum].status = 0;
        seats[selected[i].colNum - move][selected[i].rowNum].status = 1;
        selected[i] = seats[selected[i].colNum - move][selected[i].rowNum];
      }
    }
  }

  void down() {
    boolean blocked = true;
    int move = 0;
    while (blocked && move+selected[selected.length-1].rowNum < rows-1) {
      blocked = false;
      move++;

      for (int i = 0; i < selected.length; i++) {
        if (seats[selected[i].colNum][selected[i].rowNum + move].status > 1) {
          blocked = true;
          break;
        }
      }
    }
    if (!blocked) {
      for (int i = 0; i < selected.length; i++) {
        seats[selected[i].colNum][selected[i].rowNum].status = 0;
        seats[selected[i].colNum][selected[i].rowNum + move].status = 1;
        selected[i] = seats[selected[i].colNum][selected[i].rowNum + move];
      }
    }
  }

  void up() {
    boolean blocked = true;
    int move = 0;
    while (blocked && selected[0].rowNum - move > 0) {
      blocked = false;
      move++;

      for (int i = selected.length-1; i >= 0; i--) {
        if (seats[selected[i].colNum][selected[i].rowNum - move].status > 1) {
          blocked = true;
          break;
        }
      }
    }
    if (!blocked) {
      for (int i = selected.length-1; i >= 0; i--) {
        seats[selected[i].colNum][selected[i].rowNum].status = 0;
        seats[selected[i].colNum][selected[i].rowNum - move].status = 1;
        selected[i] = seats[selected[i].colNum][selected[i].rowNum - move];
      }
    }
  }
}
