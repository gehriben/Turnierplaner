package ch.zhaw.psit4.turnier.persistence;

import ch.zhaw.psit4.turnier.persistence.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseIntegrationTest<T, D> {

    @Autowired
    public BaseService<T, D> baseService;

    public abstract D getId(T t);

    public abstract T getObject();

    public abstract T changeDataField(T t);

    @Test
    public void shouldSaveEntity() {
        T t = getObject();
        T newT = baseService.createObject(t);
        assertEquals(t, newT);
    }

    @Test
    public void shouldFindObjectById() throws Exception {
        T t = getObject();
        T newT = baseService.createObject(t);
        D id = getId(newT);
        T dbT = baseService.getObjectById(id);
        assertEquals(t, dbT);
    }

    @Test
    public void shouldFindAllObjectById() throws Exception {
        T t = getObject();
        T t2 = getObject();
        T newT = baseService.createObject(t);
        T newT2 = baseService.createObject(t2);
        Iterable<T> allT = baseService.getAllObjects();
        Boolean newTFound = StreamSupport.stream(allT.spliterator(), false).anyMatch(newT::equals);
        assertTrue(newTFound);
    }

    @Test
    public void shouldDeleteAObjectById() throws Exception {
        T t = getObject();
        T t2 = getObject();
        T newT = baseService.createObject(t);
        T newT2 = baseService.createObject(t2);
        D id = getId(newT);
        baseService.deleteById(id);
        Iterable<T> allT = baseService.getAllObjects();
        Boolean newTFound = StreamSupport.stream(allT.spliterator(), false).noneMatch(newT::equals);
        assertTrue(newTFound);
    }

    @Test
    public void shouldPutAObject() throws Exception {
        T t = getObject();
        T t2 = getObject();
        T newT = baseService.createObject(t);
        T newT2 = baseService.createObject(t2);
        T changed = changeDataField(newT);
        baseService.updateObject(changed);
        Iterable<T> allT = baseService.getAllObjects();
        Boolean newTFound = StreamSupport.stream(allT.spliterator(), false).anyMatch(changed::equals);
        assertTrue(newTFound);
    }

}