package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.EncounterTournamentParticipant;
import org.springframework.data.repository.CrudRepository;

public interface EncounterTournamentParticipantRepository extends CrudRepository<EncounterTournamentParticipant, Integer> {
}
