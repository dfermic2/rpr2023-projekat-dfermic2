package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KorisnikDao;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class KorisnikManagerTest {



    @Test
    public void testFindByEmail() throws DolinaSreceException {
        KorisnikDao korisnikDao = mock(KorisnikDao.class);
        MockedStatic<DaoFactory> daoFactoryMockedStatic = mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::korisnikDao).thenReturn(korisnikDao);

        KorisnikManager.findByEmail("mujo@mujo.com");

        verify(korisnikDao).getByEmail("mujo@mujo.com");

        daoFactoryMockedStatic.close();
    }

    @Test
    public void testAdd() throws DolinaSreceException {
        Korisnik korisnik = new Korisnik("Mujo", "Mujic", "mujo@mujo.com", "Mu Town 99", "mujo123");
        KorisnikDao korisnikDao = mock(KorisnikDao.class);
        MockedStatic<DaoFactory> daoFactoryMockedStatic = mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::korisnikDao).thenReturn(korisnikDao);

        KorisnikManager.add(korisnik);

        verify(korisnikDao).add(korisnik);

        daoFactoryMockedStatic.close();
    }
}