package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

/**
 * Dao interface for Korisnik model
 *
 * @author Dijana Fermic
 */
public interface KorisnikDao extends Dao<Korisnik> {
    /**
     * get Korisnik entity from database with given email
     * @param email String containing user email
     * @return Korisnik entity from database
     * @throws DolinaSreceException - user defined exception
     */
    Korisnik getByEmail(String email) throws DolinaSreceException;
}
