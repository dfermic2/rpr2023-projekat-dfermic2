package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Kucica;
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

    public void setData(Kucica kucica) {
        ime.setText(kucica.getIme());
        cijena.setText(cijena + "KM");

        if (kucica.isJacuzzi()) jacuzzi.setText("Jacuzzi");
        else jacuzzi.setText("");

        Image image = new Image(new ByteArrayInputStream(kucica.getSlika()));
        slika.setImage(image);
    }
}
