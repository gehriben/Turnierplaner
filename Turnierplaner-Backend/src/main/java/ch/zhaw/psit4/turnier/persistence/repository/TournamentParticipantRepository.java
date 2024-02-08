package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.TournamentParticipant;
import org.springframework.data.repository.CrudRepository;

public interface TournamentParticipantRepository extends CrudRepository<TournamentParticipant, Integer> {
}
