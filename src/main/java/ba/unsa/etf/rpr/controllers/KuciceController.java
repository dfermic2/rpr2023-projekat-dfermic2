package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KuciceController implements Initializable {

    @FXML
    TilePane kuciceLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Kucica> kucicaList = KucicaManager.getAll();

            for (Kucica kucica : kucicaList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucica.fxml"));
                VBox vBox = fxmlLoader.load();
                KucicaController kucicaController = fxmlLoader.getController();
                kucicaController.setData(kucica);
                kuciceLayout.getChildren().add(vBox);
            }

        } catch (IOException | DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }
}
