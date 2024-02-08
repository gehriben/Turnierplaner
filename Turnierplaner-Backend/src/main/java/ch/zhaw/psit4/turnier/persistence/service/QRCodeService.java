package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Encounter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeService {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    private void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    public String getQRCodeForEncounter(String outObject) throws Exception {
        String path = "./"+System.currentTimeMillis()+".png";
        generateQRCodeImage(String.valueOf(outObject), WIDTH, HEIGHT,path);
        return path;
    }

    public void getQRCodeForInit() {
        String ip;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            generateQRCodeImage(socket.getLocalAddress().getHostAddress(), WIDTH, HEIGHT,"./"+System.currentTimeMillis()+".png");

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

}
