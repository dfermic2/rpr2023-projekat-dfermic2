package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class KucicaController {

    @FXML
    private Label ime, jacuzzi, cijena;

    @FXML
    private ImageView slika;

    @FXML
    private Button rezervisanje;

    Kucica kucica;

    public void setData(Kucica kucica) {
        ime.setText(kucica.getIme());
        cijena.setText(kucica.getCijena() + "€");

        if (kucica.isJacuzzi()) jacuzzi.setText("Jacuzzi");
        else jacuzzi.setText("Nema Jacuzzi");

        Image image = new Image(new ByteArrayInputStream(kucica.getSlika()));
        slika.setImage(image);

        this.kucica = kucica;
    }

    public void disableButton() {
        rezervisanje.setDisable(true);
    }

    public void onRezervisi() throws DolinaSreceException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucice.fxml"));
        Parent root = fxmlLoader.load();
        KuciceController kuciceController = fxmlLoader.getController();
        kuciceController.saveRezervacija(kucica);
    }
}
