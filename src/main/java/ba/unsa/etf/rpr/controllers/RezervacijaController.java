package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.business.RezervacijaManager;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * JavaFX controller for showing, filtering and reserving object Kucica
 *
 * @author Dijana Fermic
 */
public class RezervacijaController implements Initializable {

    @FXML
    TilePane kuciceLayout;

    @FXML
    DatePicker pocetakDate, krajDate;

    private static LocalDate pocetak;
    private static LocalDate kraj;
    private static final Rezervacija rezervacija = new Rezervacija();
    private final List<Kucica> kucicaList;

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
            for (Kucica kucica : kucicaList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucica.fxml"));
                VBox vBox = fxmlLoader.load();
                KucicaController kucicaController = fxmlLoader.getController();
                kucicaController.setData(kucica, kucica.getCijena());
                kucicaController.disableButton();
                kuciceLayout.getChildren().add(vBox);
            }

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
                    if(pocetakDate.getValue() != null) setDisable(empty || date.isBefore(pocetakDate.getValue().plusDays(1)));
                    else setDisable(empty || date.isBefore(LocalDate.now()));
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTrazi() throws IOException {
        Set<Integer> kuciceId = RezervacijaManager.findBetweenDates(pocetakDate.getValue());
        kuciceId.addAll(RezervacijaManager.findBetweenDates(krajDate.getValue()));
        List<Kucica> kuciceFiltered = kucicaList.stream().filter(kucica -> !kuciceId.contains(kucica.getId())).collect(Collectors.toList());
        kuciceLayout.getChildren().clear();

        for (Kucica kucica : kuciceFiltered) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/kucica.fxml"));
            VBox vBox = fxmlLoader.load();
            KucicaController kucicaController = fxmlLoader.getController();
            kucicaController.setData(kucica, returnUkupnaCijena(kucica.getCijena()));
            kuciceLayout.getChildren().add(vBox);
        }
    }

    public void getPocetakDate() {
        pocetak = pocetakDate.getValue();
    }

    public void getKrajDate() {
        kraj = krajDate.getValue();
    }

    public void saveIdKorisnik(int idKorisnik) {
        rezervacija.setIdKorisnik(idKorisnik);
    }

    public void saveRezervacija(Kucica kucica, ActionEvent actionEvent) throws DolinaSreceException, IOException {
        rezervacija.setPocetak(pocetak);
        rezervacija.setKraj(kraj);
        rezervacija.setIdKucica(kucica.getId());
        rezervacija.setCijena(returnUkupnaCijena(kucica.getCijena()));

        reloadRezervacije(actionEvent);

        RezervacijaManager.add(rezervacija);
    }

    private BigDecimal returnUkupnaCijena(BigDecimal cijena) {
        return cijena.multiply(BigDecimal.valueOf(DAYS.between(pocetak, kraj)));
    }

    private void reloadRezervacije(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rezervacija.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
