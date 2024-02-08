package ch.zhaw.psit4.turnier.persistence.controller;

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
public abstract class BaseControllerTest<T, D> {

    @Autowired
    public BaseRestController<T, D> baseRestController;

    public abstract D getId(T t);

    public abstract T getObject();

    public abstract T changeDataField(T t);

    @Test
    public void shouldSaveEntity() {
        T t = getObject();
        T newT = baseRestController.createObject(t);
        assertEquals(t, newT);
    }

    @Test
    public void shouldFindObjectById() throws Exception {
        T t = getObject();
        T newT = baseRestController.createObject(t);
        D id = getId(newT);
        T dbT = baseRestController.getObjectById(id);
        assertEquals(t, dbT);
    }

    @Test
    public void shouldFindAllObjectById() throws Exception {
        T t = getObject();
        T t2 = getObject();
        T newT = baseRestController.createObject(t);
        T newT2 = baseRestController.createObject(t2);
        Iterable<T> allT = baseRestController.getAllObjects();
        Boolean newTFound = StreamSupport.stream(allT.spliterator(), false).anyMatch(newT::equals);
        assertTrue(newTFound);
    }

    @Test
    public void shouldDeleteAObjectById() throws Exception {
        T t = getObject();
        T t2 = getObject();
        T newT = baseRestController.createObject(t);
        T newT2 = baseRestController.createObject(t2);
        D id = getId(newT);
        baseRestController.deleteById(id);
        Iterable<T> allT = baseRestController.getAllObjects();
        Boolean newTFound = StreamSupport.stream(allT.spliterator(), false).noneMatch(newT::equals);
        assertTrue(newTFound);
    }

    @Test
    public void shouldPutAObject() throws Exception {
        T t = getObject();
        T t2 = getObject();
        T newT = baseRestController.createObject(t);
        T newT2 = baseRestController.createObject(t2);
        T changed = changeDataField(newT);
        baseRestController.updateObject(getId(newT),changed);
        Iterable<T> allT = baseRestController.getAllObjects();
        Boolean newTFound = StreamSupport.stream(allT.spliterator(), false).anyMatch(changed::equals);
        assertTrue(newTFound);
    }

}