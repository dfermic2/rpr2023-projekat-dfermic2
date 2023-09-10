package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.business.RezervacijaManager;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class KuciceController implements Initializable {

    @FXML
    TilePane kuciceLayout;

    @FXML
    DatePicker pocetakDate, krajDate;

    List<Kucica> kucicaList;

    {
        try {
            kucicaList = KucicaManager.getAll();
        } catch (DolinaSreceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            showKucice(kucicaList);
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

            pocetakDate.setValue(LocalDate.now());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTrazi(ActionEvent actionEvent) throws IOException {
        //Uzmi sve kucice kod kojih su ispunjeni uvijeti
        Set<Integer> kuciceId = RezervacijaManager.findBetweenDates(pocetakDate.getValue());
        kuciceId.addAll(RezervacijaManager.findBetweenDates(krajDate.getValue()));

        List<Kucica> kuciceFiltered = kucicaList.stream().filter(kucica -> !kuciceId.contains(kucica.getId())).collect(Collectors.toList());

        kuciceLayout.getChildren().clear();
        showKucice(kuciceFiltered);

        System.out.println("KUCICA: " + kuciceFiltered);
    }

    private void showKucice(List<Kucica> kucicaList) throws IOException {
        for (Kucica kucica : kucicaList) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucica.fxml"));
            VBox vBox = fxmlLoader.load();
            KucicaController kucicaController = fxmlLoader.getController();
            kucicaController.setData(kucica);
            kuciceLayout.getChildren().add(vBox);
        }
    }
}
