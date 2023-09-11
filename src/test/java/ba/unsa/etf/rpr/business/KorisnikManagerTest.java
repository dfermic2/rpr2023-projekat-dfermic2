package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KorisnikDao;
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
    }

    public void testAdd() {
    }
}