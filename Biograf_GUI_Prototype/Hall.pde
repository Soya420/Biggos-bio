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
    int selectedLen = 0;
    
    for (int i = 0; i < billetter_dropList.getSelectedIndex()+1; i++) {
      seats[i][0].status = 1;
      selected[selectedLen] = seats[i][0];
      selectedLen++;
    }
    seats[5][5].status = 2;
    //seats[7][5].status = 2;
  }

  void display() {
    noStroke();
    for (int i = 0; i < cols; i++) for (int j = 0; j < rows; j++) {
      switch (seats[i][j].status) {
      case 0:
        fill(0, 255, 0);
        break;
      case 1:
        fill(0, 0, 255);
        break;
      case 2:
        fill(255, 0, 0);
        break;
      case 3:
        noFill();
        break;
      }
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
}
