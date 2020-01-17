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
    if (status == 0) fill(0, 255, 0);
    int size = min(wid, hei);
    rect(colNum*wid + 330 + size/2, rowNum*hei + 100 + size/2, size/2, size/2, 2);
  }
  
}
