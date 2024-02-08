package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Group;
import ch.zhaw.psit4.turnier.model.GroupDefinition;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.model.TournamentParticipant;
import ch.zhaw.psit4.turnier.persistence.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GroupService extends BaseService<Group, Integer> {

    @Autowired
    private GroupRepository baseRepository;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TournamentParticipantService tournamentParticipantService;

    @Override
    public CrudRepository getRepository() {
        return baseRepository;
    }

    public List<Group> createGroups(Integer tournamentId) throws Exception {
        Tournament tournament = tournamentService.getObjectById(tournamentId);
        if (tournament.getGroups().size() > 0) {
            throw new IllegalArgumentException("The tournament specified by the id has already groups.");
        }

        List<Group> groups = new ArrayList<>();
        int numberOfGroups = getNumberOfGroups(tournament);
        String groupName;
        for (int i = 0; i < numberOfGroups; i++) {
            groupName = "Group " + i + " in tournament " + tournamentId;
            Group group = new Group();
            group.setGroupName(groupName);
            group.setTournament(tournament);
            groups.add(group);
        }

        // Add groups to the tournament and update that
        // instead of creating groups on the GroupService because
        // otherwise the tournament object does not contain the groups (via getGroups()).
        // The tournament object is cached during a session and not retrieve despite newly created groups.
        tournament.setGroups(new HashSet<>(groups));
        tournamentService.updateObject(tournament);

        return groups;
    }

    public List<Group> distributeTournamentParticipantsToGroups(Integer tournamentId) throws Exception {
        Tournament tournament = tournamentService.getObjectById(tournamentId);
        Set<TournamentParticipant> tournamentParticipants = new HashSet<>(tournament.getTournamentParticipant());

        // Use an array to access groups more easily
        // todo get groups sorted by number of tournamentParticipants descending
        Group[] groups = getGroupsFromTournament(tournament);

        // Remove tournamentParticipant from set when he/she is already in a group
        tournamentParticipants.removeIf(tournamentParticipant -> tournamentParticipant.getGroup() != null);
        int numberOfGroups = groups.length;

        int counter = 0;
        for (TournamentParticipant tournamentParticipant : tournamentParticipants) {
            Group group = groups[counter % numberOfGroups];
            tournamentParticipant.setGroup(group);
            tournamentParticipantService.updateObject(tournamentParticipant);
            counter++;
        }

        return Arrays.asList(groups);
    }

    private Group[] getGroupsFromTournament(Tournament tournament) {
        Set<Group> groupsAsSet = tournament.getGroups();
        Group[] groups = new Group[groupsAsSet.size()];

        int i = 0;
        for (Group group : groupsAsSet) {
            groups[i] = group;
            i++;
        }

        return groups;
    }

    private int getNumberOfGroups(Tournament tournament) {
        int numberOfGroups = tournament.getGroupDivider();

        if (tournament.getGroupDefinition() == GroupDefinition.GROUP_SIZE) {
            numberOfGroups = (int) Math.ceil(((double) tournament.getTournamentParticipant().size()) / tournament.getGroupDivider());
        }

        return numberOfGroups;
    }
}