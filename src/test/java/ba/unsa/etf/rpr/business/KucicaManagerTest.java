package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.KucicaDao;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class KucicaManagerTest {

    @Test
    public void testFindById() throws DolinaSreceException {
        KucicaDao kucicaDao = mock(KucicaDao.class);
        MockedStatic<DaoFactory> daoFactoryMockedStatic = mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::kucicaDao).thenReturn(kucicaDao);

        KucicaManager.findById(1);

        verify(kucicaDao).getById(1);

        daoFactoryMockedStatic.close();
    }

    @Test
    public void testGetAll() throws DolinaSreceException {
        KucicaDao kucicaDao = mock(KucicaDao.class);
        MockedStatic<DaoFactory> daoFactoryMockedStatic = mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::kucicaDao).thenReturn(kucicaDao);

        KucicaManager.getAll();

        verify(kucicaDao).getAll();

        daoFactoryMockedStatic.close();
    }
}