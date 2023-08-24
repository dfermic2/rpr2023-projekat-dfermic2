package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

public interface KorisnikDao extends Dao<Korisnik> {
    Korisnik getByEmail(String email) throws DolinaSreceException;
}
