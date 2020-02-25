class Hall {
  int ID;
  int cols, rows;
  Seat[][] seats;
  Seat[] selected;

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

    //laver array for valgte sæder
    selected = new Seat[billetter_dropList.getSelectedIndex()+1];

    for (int i = 0; i < billetter_dropList.getSelectedIndex()+1; i++) {
      seats[i][0].status = 1;
      selected[i] = seats[i][0];
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
    if (selected.length == 1) reserver_label.setText("Række "+(selected[0].rowNum+1)+"\nSæde "+(selected[0].colNum+1));
    else reserver_label.setText("Række "+(selected[0].rowNum+1)+"\nSæde "+(selected[0].colNum+1)+" - "+(selected[selected.length-1].colNum+1));
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
