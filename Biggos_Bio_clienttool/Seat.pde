class Seat {
  int colNum, rowNum, wid, hei;
  int status;

  Seat(int cn, int rn, int w, int h) {
    colNum = cn;
    rowNum = rn;
    wid = w;
    hei = h;
    status = 0;
  }

  void display() {
    switch (status) {
    case 0:
      fill(0, 255, 0);
      break;
    case 1:
      fill(0, 0, 255);
      break;
    case 2:
      fill(255);
      break;
    case 3:
      noFill();
      break;
    }
    int size = min(wid, hei);
    rect(colNum*wid + 330 + size/2, rowNum*hei + 100 + size/2, size/2, size/2, 2);
  }
}
