package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KuciceController implements Initializable {

    @FXML
    TilePane kuciceLayout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("INITIALIZE");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucica.fxml"));
        System.out.println("LOADED");
        try {

            VBox vBox = fxmlLoader.load();
            System.out.println("DAIJSDOIJSA");
            KucicaController kucicaController = fxmlLoader.getController();
            kucicaController.setData(KucicaManager.findById(1));

            kuciceLayout.getChildren().add(vBox);
        } catch (IOException | DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }
}
