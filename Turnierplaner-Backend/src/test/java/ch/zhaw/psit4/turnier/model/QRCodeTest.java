package ch.zhaw.psit4.turnier.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QRCodeTest {

    private QRCode qrCodeUnderTest = new QRCode("base64");


    @Test
    public void testSetBase64() {
        // Setup
        final String base64 = "base64";

        // Run the test
        qrCodeUnderTest.setBase64(base64);

        // Verify the results 
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = qrCodeUnderTest;

        // Run the test
        final boolean result = qrCodeUnderTest.equals(o);

        // Verify the results 
        assertTrue(result);
    }

}
