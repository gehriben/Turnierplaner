package ch.zhaw.psit4.turnier.model;

import java.util.function.Consumer;
import java.util.function.Supplier;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class BaseModelTest {


    public <T extends Object> void basicGetSetObjectTest(Supplier<T> supplier, Consumer<T> consumer, T value){
        assertNull(supplier.get());
        consumer.accept(value);
        assertEquals(value,supplier.get());
    }


    public <T extends Object> void basicGetSetSetTest(Supplier<T> supplier, Consumer<Set> consumer, T value){
        Set<T> set = new HashSet<>();
        assertEquals(set,supplier.get());
        set.add(value);
        assertNotEquals(set,supplier.get());
        consumer.accept(set);
        assertEquals(set,supplier.get());
    }
}
