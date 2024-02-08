package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.persistence.service.BaseService;
import ch.zhaw.psit4.turnier.persistence.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("participants")
public class ParticipantController extends BaseRestController<Participant, Integer> {

    @Autowired
    private ParticipantService service;

    @Override
    public BaseService<Participant, Integer> getService() {
        return service;
    }
}
