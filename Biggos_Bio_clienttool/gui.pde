/* =========================================================
 * ====                   WARNING                        ===
 * =========================================================
 * The code in this tab has been generated from the GUI form
 * designer and care should be taken when editing this file.
 * Only add/edit code inside the event handlers i.e. only
 * use lines between the matching comment tags. e.g.
 
 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
 // It is safe to enter your event code here  
 } //_CODE_:button1:12356:
 
 * Do not rename this tab!
 * =========================================================
 */

public void billetter_dropList_click(GDropList source, GEvent event) { //_CODE_:billetter_dropList:237351:
} //_CODE_:billetter_dropList:237351:

public void reserver_button_click(GButton source, GEvent event) { //_CODE_:reserver_button:502515:
  if (!reserver_label.getText().equals("Ingen film valgt")) {
    if (!reserver_label.getText().equals("Ingen ledige pladser ved siden af hinanden")) {
      logind_window.setVisible(true);
    }
  }
} //_CODE_:reserver_button:502515:

public void film_dropList_click(GDropList source, GEvent event) { //_CODE_:film_dropList:361568:
} //_CODE_:film_dropList:361568:

public void afbestil_button_click(GButton source, GEvent event) { //_CODE_:afbestil_button:806271:
  afbestil_window.setVisible(true);
} //_CODE_:afbestil_button:806271:

public void biograf_dropList_click(GDropList source, GEvent event) { //_CODE_:biograf_dropList:871773:
} //_CODE_:biograf_dropList:871773:

synchronized public void win_draw2(PApplet appc, GWinData data) { //_CODE_:afbestil_window:889572:
  appc.background(78, 80, 87);
} //_CODE_:afbestil_window:889572:

public void afbestil_final_button_click(GButton source, GEvent event) { //_CODE_:afbetil_final_button:901157:
  if (!containsIllegalChar(adg_afbestil_textfield.getText())) {
    if (sh.cancel(tlf_afbestil_textfield.getText(), adg_afbestil_textfield.getText())) {
      if (!reserver_label.getText().equals("Ingen film valgt")) sh.getData("seats%"+allHalls[film_dropList.getSelectedIndex()].ID);
      afbestil_final_label.setText("");
      afbestil_window.setVisible(false);
    } else {
      afbestil_final_label.setText("Forkert brugernavn eller adgangskode");
    }
  } else {
    afbestil_final_label.setText("Forkert brugernavn eller adgangskode");
  }
} //_CODE_:afbetil_final_button:901157:

public void tlf_afbestil_textfield_change(GTextField source, GEvent event) { //_CODE_:tlf_afbestil_textfield:462433:
} //_CODE_:tlf_afbestil_textfield:462433:

public void adg_afbestil_textfield_change(GTextField source, GEvent event) { //_CODE_:adg_afbestil_textfield:726572:
} //_CODE_:adg_afbestil_textfield:726572:

synchronized public void win_draw1(PApplet appc, GWinData data) { //_CODE_:logind_window:857078:
  appc.background(78, 80, 87);
} //_CODE_:logind_window:857078:

public void tlf_logind_textfield_change(GTextField source, GEvent event) { //_CODE_:tlf_logind_textfield:973997:
} //_CODE_:tlf_logind_textfield:973997:

public void adg_logind_textfield_change(GTextField source, GEvent event) { //_CODE_:adg_logind_textfield:620999:
} //_CODE_:adg_logind_textfield:620999:

public void nybruger_button_click(GButton source, GEvent event) { //_CODE_:nybruger_button:361476:
  nybruger_window.setVisible(true);
} //_CODE_:nybruger_button:361476:

public void logind_button_click(GButton source, GEvent event) { //_CODE_:logind_button:364978:
  if (!reserver_label.getText().equals("Ingen film valgt")) {
    if (!reserver_label.getText().equals("Ingen ledige pladser ved siden af hinanden")) {
      if (!containsIllegalChar(tlf_logind_textfield.getText())) {
        if (sh.reserve(tlf_logind_textfield.getText(), adg_logind_textfield.getText())) {

          sh.getData("seats%"+allHalls[film_dropList.getSelectedIndex()].ID);

          logind_label.setText("");
          logind_window.setVisible(false);
        } else {
          logind_label.setText("Forkert brugernavn eller adgangskode");
        }
      } else {
        logind_label.setText("Forkert brugernavn eller adgangskode");
      }
    } else {
      logind_label.setText("Ingen ledige sæder ved siden af hinanden");
    }
  } else {
    logind_label.setText("Ingen film valgt");
  }
} //_CODE_:logind_button:364978:

synchronized public void win_draw3(PApplet appc, GWinData data) { //_CODE_:nybruger_window:682301:
  appc.background(78, 80, 87);
} //_CODE_:nybruger_window:682301:

public void tlf_nybruger_textfield_change(GTextField source, GEvent event) { //_CODE_:tlf_nybruger_textfield:926153:
} //_CODE_:tlf_nybruger_textfield:926153:

public void adg1_nybruger_textfield_change(GTextField source, GEvent event) { //_CODE_:adg1_nybruger_textfield:444830:
} //_CODE_:adg1_nybruger_textfield:444830:

public void adg2_nybruger_textfield_change(GTextField source, GEvent event) { //_CODE_:adg2_nybruger_textfield:456173:
} //_CODE_:adg2_nybruger_textfield:456173:

public void nybruger_final_button_click(GButton source, GEvent event) { //_CODE_:nybruger_final_button:967169:
  String username = tlf_nybruger_textfield.getText();
  String password = adg1_nybruger_textfield.getText();

  if (username.length() == 8 && isInteger(username)) {

    if (!containsIllegalChar(password)) {

      if (password.equals(adg2_nybruger_textfield.getText())) {
        if (sh.createUser(username, password)) {

          nybruger_final_label.setText("");
          tlf_nybruger_textfield.setText("");
          adg1_nybruger_textfield.setText("");
          adg2_nybruger_textfield.setText("");
          nybruger_window.setVisible(false);
        } else {
          nybruger_final_label.setText("Telefonnummer eksisterer allerede");
        }
      } else {
        nybruger_final_label.setText("Forkert gentaget adgangskode");
      }
    } else {
      nybruger_final_label.setText("Adgangskode indeholder ugyldigt tegn");
    }
  } else {
    nybruger_final_label.setText("Telefonnummer skal være på 8 cifre");
  }
} //_CODE_:nybruger_final_button:967169:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI() {
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setMouseOverEnabled(false);
  surface.setTitle("Biograf oversigt");
  billetter_dropList = new GDropList(this, 40, 230, 190, 300, 5, 30);
  billetter_dropList.setItems(loadStrings("list_237351"), 0);
  billetter_dropList.setLocalColorScheme(GCScheme.SCHEME_9);
  billetter_dropList.addEventHandler(this, "billetter_dropList_click");
  reserver_button = new GButton(this, 40, 620, 190, 50);
  reserver_button.setText("Reserver");
  reserver_button.setLocalColorScheme(GCScheme.SCHEME_8);
  reserver_button.addEventHandler(this, "reserver_button_click");
  film_dropList = new GDropList(this, 40, 120, 190, 200, 3, 30);
  film_dropList.setItems(loadStrings("list_361568"), 0);
  film_dropList.setLocalColorScheme(GCScheme.SCHEME_9);
  film_dropList.addEventHandler(this, "film_dropList_click");
  afbestil_button = new GButton(this, 1040, 620, 190, 50);
  afbestil_button.setText("Afbestil");
  afbestil_button.setLocalColorScheme(GCScheme.SCHEME_8);
  afbestil_button.addEventHandler(this, "afbestil_button_click");
  afbestil_label = new GLabel(this, 1000, 540, 280, 70);
  afbestil_label.setTextAlign(GAlign.CENTER, GAlign.BOTTOM);
  afbestil_label.setText("Har du billetter der skal afbestilles?");
  afbestil_label.setLocalColorScheme(GCScheme.SCHEME_10);
  afbestil_label.setOpaque(false);
  reserver_label = new GLabel(this, 10, 530, 250, 80);
  reserver_label.setTextAlign(GAlign.CENTER, GAlign.BOTTOM);
  reserver_label.setText("Ingen film valgt");
  reserver_label.setLocalColorScheme(GCScheme.SCHEME_10);
  reserver_label.setOpaque(false);
  biograf_dropList = new GDropList(this, 40, 10, 190, 200, 3, 30);
  biograf_dropList.setItems(loadStrings("list_871773"), 0);
  biograf_dropList.setLocalColorScheme(GCScheme.SCHEME_9);
  biograf_dropList.addEventHandler(this, "biograf_dropList_click");
  afbestil_window = GWindow.getWindow(this, "Afbestil billetter", 0, 0, 500, 500, JAVA2D);
  afbestil_window.noLoop();
  afbestil_window.setActionOnClose(G4P.KEEP_OPEN);
  afbestil_window.addDrawHandler(this, "win_draw2");
  afbetil_final_button = new GButton(afbestil_window, 160, 290, 170, 50);
  afbetil_final_button.setText("Afbestil billetter");
  afbetil_final_button.setLocalColorScheme(GCScheme.SCHEME_8);
  afbetil_final_button.addEventHandler(this, "afbestil_final_button_click");
  tlf_afbestil_textfield = new GTextField(afbestil_window, 140, 100, 210, 50, G4P.SCROLLBARS_NONE);
  tlf_afbestil_textfield.setPromptText("Telefon nr.");
  tlf_afbestil_textfield.setLocalColorScheme(GCScheme.SCHEME_8);
  tlf_afbestil_textfield.setOpaque(true);
  tlf_afbestil_textfield.addEventHandler(this, "tlf_afbestil_textfield_change");
  adg_afbestil_textfield = new GTextField(afbestil_window, 140, 190, 210, 50, G4P.SCROLLBARS_NONE);
  adg_afbestil_textfield.setPromptText("Adgangskode");
  adg_afbestil_textfield.setLocalColorScheme(GCScheme.SCHEME_8);
  adg_afbestil_textfield.setOpaque(true);
  adg_afbestil_textfield.addEventHandler(this, "adg_afbestil_textfield_change");
  afbestil_final_label = new GLabel(afbestil_window, 100, 270, 290, 20);
  afbestil_final_label.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  afbestil_final_label.setLocalColorScheme(GCScheme.SCHEME_10);
  afbestil_final_label.setOpaque(false);
  logind_window = GWindow.getWindow(this, "Log ind", 0, 0, 500, 500, JAVA2D);
  logind_window.noLoop();
  logind_window.setActionOnClose(G4P.KEEP_OPEN);
  logind_window.addDrawHandler(this, "win_draw1");
  tlf_logind_textfield = new GTextField(logind_window, 140, 100, 210, 50, G4P.SCROLLBARS_NONE);
  tlf_logind_textfield.setPromptText("Telefon nr.");
  tlf_logind_textfield.setLocalColorScheme(GCScheme.SCHEME_8);
  tlf_logind_textfield.setOpaque(true);
  tlf_logind_textfield.addEventHandler(this, "tlf_logind_textfield_change");
  adg_logind_textfield = new GTextField(logind_window, 140, 190, 210, 50, G4P.SCROLLBARS_NONE);
  adg_logind_textfield.setPromptText("Adgangskode");
  adg_logind_textfield.setLocalColorScheme(GCScheme.SCHEME_8);
  adg_logind_textfield.setOpaque(true);
  adg_logind_textfield.addEventHandler(this, "adg_logind_textfield_change");
  nybruger_button = new GButton(logind_window, 200, 390, 90, 40);
  nybruger_button.setText("Opret ny profil");
  nybruger_button.setLocalColorScheme(GCScheme.SCHEME_8);
  nybruger_button.addEventHandler(this, "nybruger_button_click");
  logind_button = new GButton(logind_window, 140, 290, 210, 30);
  logind_button.setText("Log ind");
  logind_button.setLocalColorScheme(GCScheme.SCHEME_8);
  logind_button.addEventHandler(this, "logind_button_click");
  nybruger_label = new GLabel(logind_window, 140, 360, 210, 30);
  nybruger_label.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  nybruger_label.setText("Har du ikke en bruger?");
  nybruger_label.setLocalColorScheme(GCScheme.SCHEME_10);
  nybruger_label.setOpaque(false);
  logind_label = new GLabel(logind_window, 100, 270, 290, 20);
  logind_label.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  logind_label.setLocalColorScheme(GCScheme.SCHEME_10);
  logind_label.setOpaque(false);
  nybruger_window = GWindow.getWindow(this, "Opret ny bruger", 0, 0, 500, 500, JAVA2D);
  nybruger_window.noLoop();
  nybruger_window.setActionOnClose(G4P.KEEP_OPEN);
  nybruger_window.addDrawHandler(this, "win_draw3");
  tlf_nybruger_textfield = new GTextField(nybruger_window, 140, 100, 210, 50, G4P.SCROLLBARS_NONE);
  tlf_nybruger_textfield.setPromptText("Nyt telefon nr.");
  tlf_nybruger_textfield.setLocalColorScheme(GCScheme.SCHEME_8);
  tlf_nybruger_textfield.setOpaque(true);
  tlf_nybruger_textfield.addEventHandler(this, "tlf_nybruger_textfield_change");
  adg1_nybruger_textfield = new GTextField(nybruger_window, 140, 190, 210, 50, G4P.SCROLLBARS_NONE);
  adg1_nybruger_textfield.setPromptText("Ny adgangskode");
  adg1_nybruger_textfield.setLocalColorScheme(GCScheme.SCHEME_8);
  adg1_nybruger_textfield.setOpaque(true);
  adg1_nybruger_textfield.addEventHandler(this, "adg1_nybruger_textfield_change");
  adg2_nybruger_textfield = new GTextField(nybruger_window, 140, 260, 210, 50, G4P.SCROLLBARS_NONE);
  adg2_nybruger_textfield.setPromptText("Gentag adgangskode");
  adg2_nybruger_textfield.setLocalColorScheme(GCScheme.SCHEME_8);
  adg2_nybruger_textfield.setOpaque(true);
  adg2_nybruger_textfield.addEventHandler(this, "adg2_nybruger_textfield_change");
  nybruger_final_button = new GButton(nybruger_window, 160, 350, 170, 50);
  nybruger_final_button.setText("Opret bruger");
  nybruger_final_button.setLocalColorScheme(GCScheme.SCHEME_8);
  nybruger_final_button.addEventHandler(this, "nybruger_final_button_click");
  nybruger_final_label = new GLabel(nybruger_window, 100, 330, 290, 20);
  nybruger_final_label.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  nybruger_final_label.setLocalColorScheme(GCScheme.SCHEME_10);
  nybruger_final_label.setOpaque(false);
  afbestil_window.loop();
  logind_window.loop();
  nybruger_window.loop();
}

// Variable declarations 
// autogenerated do not edit
GDropList billetter_dropList; 
GButton reserver_button; 
GDropList film_dropList; 
GButton afbestil_button; 
GLabel afbestil_label; 
GLabel reserver_label; 
GDropList biograf_dropList; 
GWindow afbestil_window;
GButton afbetil_final_button; 
GTextField tlf_afbestil_textfield; 
GTextField adg_afbestil_textfield; 
GLabel afbestil_final_label; 
GWindow logind_window;
GTextField tlf_logind_textfield; 
GTextField adg_logind_textfield; 
GButton nybruger_button; 
GButton logind_button; 
GLabel nybruger_label; 
GLabel logind_label; 
GWindow nybruger_window;
GTextField tlf_nybruger_textfield; 
GTextField adg1_nybruger_textfield; 
GTextField adg2_nybruger_textfield; 
GButton nybruger_final_button; 
GLabel nybruger_final_label; 
