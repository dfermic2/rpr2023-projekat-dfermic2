package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField email, password;

    @FXML
    public Label loginMessage;

    public void onLogin(javafx.event.ActionEvent actionEvent) throws DolinaSreceException, IOException {

//        if (email.getText().isEmpty()) loginMessage.setText("Unesite email!");
//        else if (password.getText().isEmpty()) loginMessage.setText("Unesite password!");
//        else {
//            Korisnik korisnik = KorisnikManager.findByEmail(email.getText());
//            if (korisnik == null) loginMessage.setText("Unijeli ste neispravne podatke!");
//            else if (!korisnik.getPassword().equals(password.getText()))
//                loginMessage.setText("Unijeli ste neispravne podatke!");
//            else {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucice.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
//            }
//        }
    }
}
