package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.Round;
import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Integer> {
}
