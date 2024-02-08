package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.Encounter;
import ch.zhaw.psit4.turnier.model.QRCode;
import ch.zhaw.psit4.turnier.model.QRCodeTransfer;
import ch.zhaw.psit4.turnier.persistence.service.BaseService;
import ch.zhaw.psit4.turnier.persistence.service.EncounterService;
import ch.zhaw.psit4.turnier.persistence.service.QRCodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

@RestController
@RequestMapping("encounters")
public class EncounterController extends BaseRestController<Encounter, Integer> {

    @Autowired
    private EncounterService service;

    @Override
    public BaseService<Encounter, Integer> getService() {
        return service;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "{encounter_id}/qrcode")
    public QRCode getQRCodeForEncounter(@PathVariable(value = "encounter_id") Integer encounter_id) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        QRCodeService qrCodeGenerator = new QRCodeService();
        QRCodeTransfer qrCodeTransfer = new QRCodeTransfer();
        qrCodeTransfer.setEncounterId(encounter_id);
        String path = qrCodeGenerator.getQRCodeForEncounter(objectMapper.writeValueAsString(qrCodeTransfer));
        QRCode qrCode = new QRCode(Base64.getEncoder().encodeToString(Files.readAllBytes(new File(path).toPath())));
        new File(path).delete();
        return qrCode;
    }
}
