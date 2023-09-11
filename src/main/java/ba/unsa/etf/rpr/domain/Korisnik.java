package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Korisnik {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String adresa;
    private String password;

    public Korisnik() {
    }

    //ID U KONSTRUKTORU?
    public Korisnik(String ime, String prezime, String email, String adresa, String password) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.adresa = adresa;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Korisnik {id = " + id +
                ", ime = '" + ime + '\'' +
                ", prezime = '" + prezime + '\'' +
                ", email = '" + email + '\'' +
                ", adresa = '" + adresa + '\'' +
                ", password = '" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik korisnik = (Korisnik) o;
        return id == korisnik.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, email, adresa, password);
    }
}
