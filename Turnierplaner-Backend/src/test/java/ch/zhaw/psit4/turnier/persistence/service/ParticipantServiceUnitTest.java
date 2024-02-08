package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.persistence.repository.ParticipantRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

public class ParticipantServiceUnitTest extends BaseServiceUnitTest<Participant, Integer> {

    @MockBean
    private ParticipantRepository participantRepository;

    @Override
    public CrudRepository<Participant, Integer> getCrudRepository() {
        return participantRepository;
    }

    @Override
    public ArrayList<Participant> getObjList() {
        return new ArrayList<>(Collections.singleton(new Participant()));
    }
}
