package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.Ranking;
import org.springframework.data.repository.CrudRepository;

public interface RankingRepository extends CrudRepository<Ranking, Integer> {
}
