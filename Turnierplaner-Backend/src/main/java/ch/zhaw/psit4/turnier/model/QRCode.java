package ch.zhaw.psit4.turnier.model;

import lombok.Data;

@Data
public class QRCode {
    private String base64;

    public QRCode(String base64) {
        this.base64 = base64;
    }
}
