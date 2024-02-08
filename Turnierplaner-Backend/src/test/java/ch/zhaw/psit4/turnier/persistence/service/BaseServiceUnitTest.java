package ch.zhaw.psit4.turnier.persistence.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseServiceUnitTest<T, D> {

    @Autowired
    public BaseService<T, D> baseService;

    public abstract CrudRepository<T, D> getCrudRepository();

    @Test
    public void shouldReturnAllFromRepo() {
        ArrayList<T> Ts = getObjList();
        when(getCrudRepository().findAll()).thenReturn(Ts);
        assertEquals(Ts, baseService.getAllObjects());
    }

    @Test
    public void shouldReturnObjFromRepo() throws Exception {
        T T = getObjList().get(0);
        when(getCrudRepository().findById(any())).thenReturn(Optional.of(T));
        assertEquals(T, baseService.getObjectById(any()));
        verify(getCrudRepository(), times(1)).findById(any());
    }

    @Test
    public void shouldDeleteObjFromRepo() throws Exception {
        T T = getObjList().get(0);
        baseService.deleteById(any());
        verify(getCrudRepository(), times(1)).deleteById(any());
    }

    @Test
    public void shouldCreateObject() {
        T t = getObjList().get(0);
        when(getCrudRepository().save(any())).thenReturn(t);
        assertEquals(t, baseService.createObject(t));
        verify(getCrudRepository(), times(1)).save(any());
    }

    @Test
    public void shouldUpdateObject() {
        T t = getObjList().get(0);
        when(getCrudRepository().save(any())).thenReturn(t);
        assertEquals(t, baseService.updateObject(t));
        verify(getCrudRepository(), times(1)).save(any());
    }

    public abstract ArrayList<T> getObjList();
}