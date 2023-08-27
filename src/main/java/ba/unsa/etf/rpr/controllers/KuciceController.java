package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.dao.KorisnikDao;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KuciceController implements Initializable {

    @FXML
    public ImageView kucica;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("INITIALIZED");

        try {
            Image image = new Image(new ByteArrayInputStream(KucicaManager.findById(2).getSlika()));
            kucica.setImage(image);
        } catch (DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }
}
