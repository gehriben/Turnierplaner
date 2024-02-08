package ch.zhaw.psit4.turnier.persistence.repository;

import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.model.TournamentUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {
    public List<Tournament> getAllByTournamentUsersIn(Set<TournamentUser> tournamentUsers);
    List<Tournament> OrderByTournamentDateDesc();
}
