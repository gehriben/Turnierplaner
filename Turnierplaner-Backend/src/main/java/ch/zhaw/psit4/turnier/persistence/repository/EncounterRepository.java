package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.Encounter;
import org.springframework.data.repository.CrudRepository;

public interface EncounterRepository extends CrudRepository<Encounter, Integer> {
}
