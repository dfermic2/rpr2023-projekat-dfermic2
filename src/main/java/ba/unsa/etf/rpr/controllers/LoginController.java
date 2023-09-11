package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    public TextField email, password, ime, prezime, adresa, emailRegistracija, passwordRegistracija;

    @FXML
    public Label loginMessage, registracijaMessage;

    public void onLogin(ActionEvent actionEvent) throws DolinaSreceException, IOException {
        Korisnik korisnik = new Korisnik();

        if (email.getText().isEmpty()) loginMessage.setText("Unesite email!");
        else if (password.getText().isEmpty()) loginMessage.setText("Unesite password!");
        else {
            korisnik = KorisnikManager.findByEmail(email.getText());
            if (korisnik == null) loginMessage.setText("Unijeli ste neispravne podatke!");
            else if (!korisnik.getPassword().equals(password.getText()))
                loginMessage.setText("Unijeli ste neispravne podatke!");
            else {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucice.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        KuciceController kuciceController = fxmlLoader.getController();
        kuciceController.saveIdKorisnik(korisnik.getId());

        stage.setScene(scene);
        stage.show();
            }
        }
    }

    public void onRegister(ActionEvent actionEvent) throws DolinaSreceException, IOException {
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (ime.getText().isEmpty()) registracijaMessage.setText("Unesite ime!");
        else if (prezime.getText().isEmpty()) registracijaMessage.setText("Unesite prezime!");
        else if (emailRegistracija.getText().isEmpty()) registracijaMessage.setText("Unesite email!");
        else if (adresa.getText().isEmpty()) registracijaMessage.setText("Unesite adresu!");
        else if (passwordRegistracija.getText().isEmpty()) registracijaMessage.setText("Unesite password!");
        else if (!Pattern.compile(emailRegex).matcher(emailRegistracija.getText()).matches()) registracijaMessage.setText("Neispravan format email adrese!");
        else if (KorisnikManager.findByEmail(emailRegistracija.getText()) != null) registracijaMessage.setText("Email se vec koristi!");
        else {
            Korisnik korisnik = KorisnikManager.add(new Korisnik(0, ime.getText(), prezime.getText(), emailRegistracija.getText(), adresa.getText(), passwordRegistracija.getText()));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucice.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            KuciceController kuciceController = fxmlLoader.getController();
            kuciceController.saveIdKorisnik(korisnik.getId());

            stage.setScene(scene);
            stage.show();
        }
    }
}
