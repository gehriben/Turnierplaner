package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QRCodeTransferTest extends BaseModelTest {

    private QRCodeTransfer objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new QRCodeTransfer();
    }

    @Test
    public void shouldGetAndSetEncounterId(){
        basicGetSetObjectTest(objectUnderTest::getEncounterId,objectUnderTest::setEncounterId,12);
    }
}
