package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    public TextField email, password;

     @FXML
     public Label loginMessage;

    public void onLogin() throws DolinaSreceException {

        if(email.getText().isEmpty()) loginMessage.setText("Unesite email!");
        else if(password.getText().isEmpty()) loginMessage.setText("Unesite password!");
        else {
            Korisnik korisnik = KorisnikManager.findByEmail(email.getText());
            if(korisnik == null) loginMessage.setText("Unijeli ste neispravne podatke!");
            else if (!korisnik.getPassword().equals(password.getText())) loginMessage.setText("Unijeli ste neispravne podatke!");
            else loginMessage.setText("");
        }
    }
}
