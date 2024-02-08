package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.*;
import ch.zhaw.psit4.turnier.persistence.security.UserNotLoggedInException;
import ch.zhaw.psit4.turnier.persistence.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("tournaments")
public class TournamentController extends BaseRestController<Tournament, Integer> {

    @Autowired
    private RankingService rankingService;

    @Autowired
    private TournamentService service;

    @Autowired
    private TournamentParticipantService tournamentParticipantService;

    @Autowired
    private EncounterService encounterService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoundService roundService;

    private QRCodeService qrCodeGenerator = new QRCodeService();

    @Override
    public BaseService<Tournament, Integer> getService() {
        return service;
    }

    @Override
    public Iterable<Tournament> getAllObjects() {
        try {
            User user = userService.getCurrentUser();
            if (!user.getAdmin()) {
                return service.getAllTournamentsForUser(user);
            }
        } catch (UserNotLoggedInException | Exception e) {
            return super.getAllObjects();
        }
        return super.getAllObjects();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "{tournament_id}/participants")
    public Iterable<Participant> getAllTPObjects(@PathVariable(value = "tournament_id") Integer tournamentId) throws Exception {
        return service.getAllParticipantsForTournament(tournamentId);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "{tournament_id}/groups")
    public Iterable<Group> getAllGroupObjects(@PathVariable(value = "tournament_id") Integer tournamentId) {
        return service.getAllGroupsForTournamentIdWithEncounters(tournamentId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/{tournament_id}/rounds/{round_id}/encounters")
    public List<Encounter> createEncounters(@PathVariable(value = "tournament_id") Integer tournamentId, @PathVariable(value = "round_id") Integer roundId) throws Exception {
        return encounterService.createEncounters(tournamentId, roundId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/{tournament_id}/rounds/{round_number}/groups/encounters")
    public List<Group> getEncountersInGroups(@PathVariable(value = "tournament_id") Integer tournamentId, @PathVariable(value = "round_number") Integer roundNumber) throws Exception {
        return encounterService.getEncounters(tournamentId, roundNumber);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "{tournament_id}/participants")
    public Participant createObject(@RequestBody Participant participant, @PathVariable(value = "tournament_id") Integer tournamentId) throws Exception {
        return tournamentParticipantService.createTournamentParticipant(participant, tournamentId);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "{tournament_id}/groups")
    public List<Group> createGroups(@PathVariable(value = "tournament_id") Integer tournamentId) throws Exception {
        if (getObjectById(tournamentId).getGroups().size() <= 0) {
            groupService.createGroups(tournamentId);
        }
        return groupService.distributeTournamentParticipantsToGroups(tournamentId);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "{tournament_id}/rounds")
    public Round createNextRound(@PathVariable(value = "tournament_id") Integer tournamentId) throws Exception {
        Tournament tournament = service.getObjectById(tournamentId);
        return roundService.createNextRound(tournament);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", path = "{tournament_id}/rounds/{round_number}")
    public Round updateRound(@PathVariable(value = "tournament_id") Integer tournamentId, @PathVariable(value = "round_number") Integer roundNumber) throws Exception {
        Tournament tournament = service.getObjectById(tournamentId);
        Set<Round> rounds = tournament.getRounds();
        rounds.removeIf(r -> !r.getRoundNumber().equals(roundNumber));
        Optional<Round> optionalRound = rounds.stream().findFirst();
        Round round = null;

        if (optionalRound.isPresent()) {
            round = optionalRound.get();
            round.setFinished(true);
            roundService.updateObject(round);
        }

        return round;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/{tournament_id}/rankings")
    public Ranking getTempRanking(@PathVariable(value = "tournament_id") Integer tournamentId) throws Exception {
        Tournament tournament = service.getObjectById(tournamentId);
        tournament.setTournamentState(TournamentState.FINISHED);
        service.updateObject(tournament);

        return rankingService.getTempRanking(tournamentId);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/qrCode")
    public void getInitQRCode() {
        qrCodeGenerator.getQRCodeForInit();
    }

    @Override
    public Tournament createObject(@RequestBody Tournament object) {
        try {
            TournamentUser tournamentUser = new TournamentUser();
            tournamentUser.setTournament(object);
            tournamentUser.setUser(userService.getCurrentUser());
            tournamentUser.setRole(TournamentUserRole.MANAGER);
            object.getTournamentUsers().add(tournamentUser);
        } catch (UserNotLoggedInException ignored) {

        }
        return super.createObject(object);

    }
}
