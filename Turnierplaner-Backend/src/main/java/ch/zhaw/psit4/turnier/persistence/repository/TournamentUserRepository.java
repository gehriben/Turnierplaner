package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.TournamentUser;
import org.springframework.data.repository.CrudRepository;

public interface TournamentUserRepository extends CrudRepository<TournamentUser, Integer> {
}
