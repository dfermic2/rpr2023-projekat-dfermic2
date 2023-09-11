package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.RezervacijaDao;
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

    public void testAdd() {
    }
}