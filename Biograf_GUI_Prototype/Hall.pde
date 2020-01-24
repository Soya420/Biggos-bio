class Hall {
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
    seats[5][5].status = 3;
    seats[6][5].status = 3;
    seats[7][5].status = 3;
    seats[7][6].status = 3;
    seats[7][7].status = 3;
    seats[7][8].status = 3;
    seats[5][2].status = 3;
    seats[8][2].status = 3;
    seats[3][9].status = 3;
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
  }

/*
  void moveSeats(int x, int y) {
    boolean blocked = true;
    int move = 0;
    while (blocked &&
      selected[0].colNum + x*move >= 0 &&
      selected[selected.length-1].colNum + x*move < cols-1 &&
      selected[0].rowNum + y*move >= 0 &&
      selected[selected.length-1].rowNum + y*move < rows-1) {
      blocked = false;
      move++;

      //højre og ned
      if (x == 1 || y == 1) {
        for (int i = hall.selected.length-1; i >= 0; i--) {
          if (hall.seats[hall.selected[i].colNum + x*move][hall.selected[i].rowNum + y*move].status > 1) {
            blocked = true;
            break;
          }
        }
      } else {
        //venstre og op
        for (int i = 0; i < hall.selected.length; i++) {
          if (hall.seats[hall.selected[i].colNum + x*move][hall.selected[i].rowNum + y*move].status > 1) {
            blocked = true;
            break;
          }
        }
      }
    }
    if (!blocked) {
      if (x == 1 || y == 1) {
        for (int i = hall.selected.length-1; i >= 0; i--) {
          hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum].status = 0;
          hall.seats[hall.selected[i].colNum + x*move][hall.selected[i].rowNum + y*move].status = 1;
          hall.selected[i] = hall.seats[hall.selected[i].colNum + x*move][hall.selected[i].rowNum + y*move];
        }
      } else {
        for (int i = 0; i < hall.selected.length; i++) {
          hall.seats[hall.selected[i].colNum][hall.selected[i].rowNum].status = 0;
          hall.seats[hall.selected[i].colNum + x*move][hall.selected[i].rowNum + y*move].status = 1;
          hall.selected[i] = hall.seats[hall.selected[i].colNum + x*move][hall.selected[i].rowNum + y*move];
        }
      }
    }
  }*/
}
