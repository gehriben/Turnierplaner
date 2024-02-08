package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.*;
import ch.zhaw.psit4.turnier.persistence.repository.EncounterRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class EncounterService extends BaseService<Encounter, Integer> {

    private static final Integer NUMBER_OF_SETS_PER_ENCOUNTER = 3;

    @Autowired
    private EncounterRepository baseRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RankingService rankingService;

    @Autowired
    private RoundService roundService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private GameSetService gameSetService;

    @Autowired
    private GamePointService gamePointService;

    @Override
    public CrudRepository getRepository() {
        return baseRepository;
    }

    public List<Encounter> createEncounters(Integer tournamentId, Integer roundId) throws Exception {
        List<Encounter> encounters = new ArrayList<>();

        Tournament tournament = tournamentService.getObjectById(tournamentId);
        Round round = roundService.getObjectById(roundId);
        List<List<TournamentParticipant>> lists = new ArrayList<>();
        for(Group group : tournament.getGroups()) {
            List<TournamentParticipant> tournamentParticipants = new ArrayList<>(group.getTournamentParticipant());
            Collections.shuffle(tournamentParticipants);
            lists.addAll(Lists.partition(tournamentParticipants, 2));
        }
        lists.stream().map(Encounter::new).peek(e -> e.setRound(round)).forEach(e -> encounters.add(createObject(e)));

        for (Encounter encounter : encounters) {
            for (int i = 0; i < NUMBER_OF_SETS_PER_ENCOUNTER; i++) {
                GameSet gameSet = new GameSet();
                gameSet.setSequenceNumber(i);
                gameSet.setEncounter(encounter);
                gameSetService.createObject(gameSet);

                for (EncounterTournamentParticipant encounterTournamentParticipant : encounter.getEncounterTournamentParticipants()) {
                    GamePoints gamePoints = new GamePoints();
                    gamePoints.setEncounterTournamentParticipant(encounterTournamentParticipant);
                    gamePoints.setGameSet(gameSet);
                    gamePointService.createObject(gamePoints);
                }
            }
        }

        setTournamentStatusToStarted(tournamentId);

        return encounters;
    }

    /**
     * Returns a list of groups (of the specified tournament)
     * containing the encounters for the specified round.
     */
    public List<Group> getEncounters(Integer tournamentId, Integer roundNumber) throws Exception {
        Set<Group> groups = tournamentService.getObjectById(tournamentId).getGroups();

        for (Group group : groups) {
            group.getEncounters().removeIf(encounter ->
                    !encounter.getRound().getRoundNumber().equals(roundNumber));
        }

        return newArrayList(groups);
    }

    public List<Encounter> getAllEncountersForGroup(Integer groupId) throws Exception {
        return groupService.getObjectById(groupId)
                .getTournamentParticipant()
                .stream()
                .map(TournamentParticipant::getEncounterTournamentParticipants)
                .flatMap(Collection::stream)
                .map(EncounterTournamentParticipant::getEncounter)
                .collect(Collectors.toList());
    }

    private List<TournamentParticipant> getTournamentParticipantSortedByRank(Integer tournamentId) {
        try {
             return rankingService.getTempRanking(tournamentId)
                .getRankingPlacements().stream().sorted(Comparator.comparing(RankingPlacement::getRank))
                    .map(RankingPlacement::getTournamentParticipant).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Sets the status of the specified tournament to STARTED,
     * if it is currently in the NEW status.
     */
    private void setTournamentStatusToStarted(Integer tournamentId) throws Exception {
        Tournament tournament = tournamentService.getObjectById(tournamentId);
        if (tournament.getTournamentState() == TournamentState.NEW) {
            tournament.setTournamentState(TournamentState.STARTED);
            tournamentService.updateObject(tournament);
        }
    }
}
