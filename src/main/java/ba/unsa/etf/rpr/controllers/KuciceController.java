package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class KuciceController implements Initializable {

    @FXML
    TilePane kuciceLayout;

    @FXML
    DatePicker pocetakDate, krajDate;

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


                // TODO: REFACTOR
                pocetakDate.setDayCellFactory(param -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(LocalDate.now()));
                    }
                });

                krajDate.setDayCellFactory(param -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(pocetakDate.getValue()));
                    }
                });
            }

            pocetakDate.setValue(LocalDate.now());

        } catch (IOException | DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTrazi(ActionEvent actionEvent) {
    }
}
