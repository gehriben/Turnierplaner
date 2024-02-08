package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.EncounterTournamentParticipant;
import ch.zhaw.psit4.turnier.model.GamePoints;
import ch.zhaw.psit4.turnier.model.GameSet;
import ch.zhaw.psit4.turnier.persistence.repository.GameSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;


@Service
public class GameSetService extends BaseService<GameSet, Integer> {

    @Autowired
    private GameSetRepository gameSetRepository;

    @Override
    public CrudRepository getRepository() {
        return gameSetRepository;
    }


    @Autowired
    private EncounterTournamentParticipantService etp_service;

    public GamePoints addGamepoints(Integer gameset_id, Integer etp_id, Integer points) throws Exception {
        GameSet gameSet = getObjectById(gameset_id);
        EncounterTournamentParticipant encounterTournamentParticipant = etp_service.getObjectById(etp_id);
        GamePoints gamePoints = new GamePoints();
        gamePoints.setGameSet(gameSet);
        gamePoints.setEncounterTournamentParticipant(encounterTournamentParticipant);
        gamePoints.setPoints(points);
        gameSet.getGamePoints().add(gamePoints);
        updateObject(gameSet);
        return gamePoints;
    }

    public GamePoints changeGamepoints(Integer gameset_id, Integer etp_id, Integer points) throws Exception {
        GameSet gameSet = getObjectById(gameset_id);
        GamePoints gamePoints = gameSet
                .getGamePoints()
                .stream()
                .filter(gp -> gp.getEncounterTournamentParticipant().getEncounterTournamentParticipantId().equals(etp_id))
                .findAny()
                .orElseThrow(InputMismatchException::new);
        gamePoints.setPoints(points);
        updateObject(gameSet);
        return gamePoints;
    }
}
