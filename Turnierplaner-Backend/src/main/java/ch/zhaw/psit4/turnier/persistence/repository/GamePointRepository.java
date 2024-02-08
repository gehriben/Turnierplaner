package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.GamePoints;
import org.springframework.data.repository.CrudRepository;

public interface GamePointRepository extends CrudRepository<GamePoints, Integer> {
}
