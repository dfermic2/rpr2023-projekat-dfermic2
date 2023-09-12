package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX controller for creation and alteration of Kucica object
 *
 * @author Dijana Fermic
 */
public class KucicaController implements Initializable {

    @FXML
    private Label ime, jacuzzi, cijena;

    @FXML
    private ImageView slika;

    @FXML
    private Button rezervisanje;

    Kucica kucica;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rezervisanje.setCursor(Cursor.HAND);
    }

    public void setData(Kucica kucica, BigDecimal cijenaPrikaz) {
        ime.setText(kucica.getIme());
        cijena.setText(cijenaPrikaz + "€");

        if (kucica.isJacuzzi()) jacuzzi.setText("Jacuzzi");
        else jacuzzi.setText("Nema Jacuzzi");

        Image image = new Image(new ByteArrayInputStream(kucica.getSlika()));
        slika.setImage(image);

        this.kucica = kucica;
    }

    public void disableButton() {
        rezervisanje.setDisable(true);
    }

    public void onRezervisi(ActionEvent actionEvent) throws DolinaSreceException, IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rezervacija");
        alert.setHeaderText("Uspješno ste rezervisali kucicu " + kucica.getIme());

        if (alert.showAndWait().get() == ButtonType.OK) {
            RezervacijaController rezervacijaController = new RezervacijaController();
            rezervacijaController.saveRezervacija(kucica, actionEvent);
        }
    }
}
