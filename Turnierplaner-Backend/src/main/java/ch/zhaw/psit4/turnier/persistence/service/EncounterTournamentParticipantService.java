package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.EncounterTournamentParticipant;
import ch.zhaw.psit4.turnier.persistence.repository.EncounterTournamentParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class EncounterTournamentParticipantService extends BaseService<EncounterTournamentParticipant, Integer> {

    @Autowired
    private EncounterTournamentParticipantRepository encounterTournamentParticipantRepository;

    @Override
    public CrudRepository getRepository() {
        return encounterTournamentParticipantRepository;
    }
}
