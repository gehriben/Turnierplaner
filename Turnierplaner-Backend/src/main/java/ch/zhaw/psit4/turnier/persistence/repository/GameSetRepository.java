package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.GameSet;
import org.springframework.data.repository.CrudRepository;

public interface GameSetRepository extends CrudRepository<GameSet, Integer> {
}
