package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.persistence.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService extends BaseService<Participant, Integer> {

    @Autowired
    private ParticipantRepository baseRepository;

    @Override
    public CrudRepository getRepository() {
        return baseRepository;
    }
}
