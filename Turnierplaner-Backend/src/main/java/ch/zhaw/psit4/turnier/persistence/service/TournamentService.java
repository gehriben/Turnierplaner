package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.*;
import ch.zhaw.psit4.turnier.persistence.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TournamentService extends BaseService<Tournament, Integer> {
    @Autowired
    private TournamentRepository baseRepository;

    @Autowired
    private GroupService groupService;

    @Override
    public CrudRepository getRepository() {
        return baseRepository;
    }

    public Iterable<Participant> getAllParticipantsForTournament(Integer tournament_id) throws Exception {
        return getObjectById(tournament_id)
                .getTournamentParticipant()
                .stream()
                .map(TournamentParticipant::getParticipant)
                .collect(Collectors.toList());
    }

    public Iterable<Group> getAllGroupsForTournamentIdWithEncounters(Integer tournament_id) {
        List<Group> groups = StreamSupport.stream(groupService.getAllObjects().spliterator(), false)
                .filter(group -> group.getTournamentParticipant()
                        .stream()
                        .anyMatch(tournamentParticipant -> tournamentParticipant.getTournament().getTournamentId().equals(tournament_id)))
                .collect(Collectors.toList());
        return groups;
    }

    @Override
    public Iterable<Tournament> getAllObjects() {
        return baseRepository.OrderByTournamentDateDesc();
    }

    public Iterable<Tournament> getAllTournamentsForUser(User user) throws Exception {
        Set<TournamentUser> tournamentUsers = user.getTournamentUser();
        return baseRepository.getAllByTournamentUsersIn(tournamentUsers);
    }
}
