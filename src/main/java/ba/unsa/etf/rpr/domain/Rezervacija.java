package ba.unsa.etf.rpr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Rezervacija {
    private int id;
    private int idKorisnik;
    private int idKucica;
    private Date pocetak;
    private Date kraj;
    private BigDecimal cijena;

    public Rezervacija() {
    }

    public Rezervacija(int id, int idKorisnik, int idKucica, Date pocetak, Date kraj, BigDecimal cijena) {
        this.id = id;
        this.idKorisnik = idKorisnik;
        this.idKucica = idKucica;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.cijena = cijena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public int getIdKucica() {
        return idKucica;
    }

    public void setIdKucica(int idKucica) {
        this.idKucica = idKucica;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    @Override
    public String toString() {
        return "Rezervacija {id = " + id +
                ", id korisnika = '" + idKorisnik + '\'' +
                ", id kucice = '" + idKucica + '\'' +
                ", pocetak = '" + pocetak.toString() + '\'' +
                ", kraj = '" + kraj.toString() + '\'' +
                ", cijena = '" + cijena.toString() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rezervacija rezervacija = (Rezervacija) o;
        return id == rezervacija.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idKorisnik, idKucica, pocetak, kraj, cijena);
    }
}
