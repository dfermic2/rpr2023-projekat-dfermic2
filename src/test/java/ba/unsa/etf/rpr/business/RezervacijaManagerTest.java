package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.RezervacijaDao;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class RezervacijaManagerTest {

    @Test
    public void testFindBetweenDates() {
        LocalDate date = LocalDate.now();
        RezervacijaDao rezervacijaDao = mock(RezervacijaDao.class);
        MockedStatic<DaoFactory> daoFactoryMockedStatic = mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::rezervacijaDao).thenReturn(rezervacijaDao);

        RezervacijaManager.findBetweenDates(date);

        verify(rezervacijaDao).getBetweenDates(date);

        daoFactoryMockedStatic.close();
    }

    @Test
    public void testAdd() throws DolinaSreceException {
        Rezervacija rezervacija = new Rezervacija();
        RezervacijaDao rezervacijaDao = mock(RezervacijaDao.class);
        MockedStatic<DaoFactory> daoFactoryMockedStatic = mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::rezervacijaDao).thenReturn(rezervacijaDao);

        RezervacijaManager.add(rezervacija);

        verify(rezervacijaDao).add(rezervacija);

        daoFactoryMockedStatic.close();
    }
}