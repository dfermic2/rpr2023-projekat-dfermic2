package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    public TextField email, password;
    public Button loginBtn;

    public void onLogin() throws DolinaSreceException {

        if(email.getText().isEmpty()) System.out.println("Prazan email");
        if(password.getText().isEmpty()) System.out.println("Prazan password");
        else {
            Korisnik korisnik = KorisnikManager.findByEmail(email.getText());
            if (korisnik.getPassword().equals(password.getText())) System.out.println("Ispravan password");
            else System.out.println("Pogresan password");
        }
    }
}
