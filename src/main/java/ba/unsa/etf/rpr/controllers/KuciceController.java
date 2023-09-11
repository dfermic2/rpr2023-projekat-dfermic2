package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.KucicaManager;
import ba.unsa.etf.rpr.business.RezervacijaManager;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class KuciceController implements Initializable {

    @FXML
    TilePane kuciceLayout;

    @FXML
    DatePicker pocetakDate, krajDate;
    Rezervacija rezervacija = new Rezervacija();

    static LocalDate pocetak;
    static LocalDate kraj;
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

    public void onTrazi() throws IOException {
//        rezervacija.setPocetak(returnDate(pocetakDate.getValue()));
//        rezervacija.setKraj(returnDate(krajDate.getValue()));
//
//        pocetak = pocetakDate.getValue();
//        kraj = krajDate.getValue();

        Set<Integer> kuciceId = RezervacijaManager.findBetweenDates(returnDate(pocetakDate.getValue()));
        kuciceId.addAll(RezervacijaManager.findBetweenDates(returnDate(krajDate.getValue())));

        List<Kucica> kuciceFiltered = kucicaList.stream().filter(kucica -> !kuciceId.contains(kucica.getId())).collect(Collectors.toList());

        kuciceLayout.getChildren().clear();
        showKucice(kuciceFiltered);
    }

    public void getPocetakDate() {
        pocetak = pocetakDate.getValue();
        System.out.println(pocetak.toString());
    }

    public void getKrajDate() {
        kraj = krajDate.getValue();
    }

    public void saveIdKorisnik(int idKorisnik) {
        rezervacija.setIdKorisnik(idKorisnik);
    }

    public void saveRezervacija(Kucica kucica) throws DolinaSreceException {
//        System.out.println("++++++++++");
//        System.out.println(kucica.toString());
//        System.out.println(pocetak);
//        System.out.println(pocetakDate);
//        System.out.println("++++++++++");


        rezervacija.setPocetak(returnDate(pocetak));
        rezervacija.setKraj(returnDate(kraj));
        rezervacija.setIdKucica(kucica.getId());
        rezervacija.setCijena(returnUkupnaCijena(kucica.getCijena()));

//        System.out.println(rezervacija.toString());
        RezervacijaManager.add(rezervacija);
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

    private BigDecimal returnUkupnaCijena(BigDecimal cijena) {
//        return cijena.multiply(BigDecimal.valueOf(DAYS.between(pocetak, kraj)));
        return cijena.multiply(BigDecimal.valueOf(Duration.between(pocetak.atStartOfDay(), kraj.atStartOfDay()).toDays()));
    }

    private Date returnDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
