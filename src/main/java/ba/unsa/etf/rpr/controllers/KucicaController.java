package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Kucica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

public class KucicaController {

    @FXML
    private Label ime, jacuzzi, cijena;

    @FXML
    private ImageView slika;

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

    public void onRezervisi(ActionEvent actionEvent) {
        KuciceController kuciceController = new KuciceController();
        kuciceController.saveRezervacija(kucica);
    }
}
