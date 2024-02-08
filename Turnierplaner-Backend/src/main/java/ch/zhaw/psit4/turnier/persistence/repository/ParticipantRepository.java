package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {
}
