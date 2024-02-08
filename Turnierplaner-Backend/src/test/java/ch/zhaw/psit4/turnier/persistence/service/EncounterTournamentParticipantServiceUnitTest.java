package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.EncounterTournamentParticipant;
import ch.zhaw.psit4.turnier.model.TournamentUser;
import ch.zhaw.psit4.turnier.persistence.repository.EncounterTournamentParticipantRepository;
import ch.zhaw.psit4.turnier.persistence.repository.TournamentUserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

public class EncounterTournamentParticipantServiceUnitTest extends BaseServiceUnitTest<EncounterTournamentParticipant, Integer> {

    @MockBean
    private EncounterTournamentParticipantRepository groupRepository;

    @Override
    public CrudRepository<EncounterTournamentParticipant, Integer> getCrudRepository() {
        return groupRepository;
    }

    @Override
    public ArrayList<EncounterTournamentParticipant> getObjList() {
        return new ArrayList<>(Collections.singleton(new EncounterTournamentParticipant()));
    }
}
