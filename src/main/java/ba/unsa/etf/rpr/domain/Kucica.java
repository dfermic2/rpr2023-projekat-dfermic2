package ba.unsa.etf.rpr.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * Model of Kucica
 *
 * @author Dijana Fermic
 */
public class Kucica {
    private int id;
    private String ime;
    private BigDecimal cijena;
    private boolean jacuzzi;
    private byte[] slika;

    public Kucica() {
    }

    public Kucica(String ime, BigDecimal cijena, boolean jacuzzi, byte[] slika) {
        this.ime = ime;
        this.cijena = cijena;
        this.jacuzzi = jacuzzi;
        this.slika = slika;
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

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public byte[] getSlika() {
        return slika;
    }

    public void setSlika(byte[] slika) {
        this.slika = slika;
    }

    @Override
    public String toString() {
        return "Kucica{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", cijena=" + cijena +
                ", jacuzzi=" + jacuzzi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kucica kucica = (Kucica) o;
        return id == kucica.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, cijena, jacuzzi, Arrays.hashCode(slika));
    }
}
