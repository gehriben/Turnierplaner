package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.*;
import ch.zhaw.psit4.turnier.persistence.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GameSetControllerTest extends BaseControllerTest<GameSet, Integer> {

    @Autowired
    private EncounterRepository encounterRepository;

    @Override
    public Integer getId(GameSet gameSet) {
        return gameSet.getSetId();
    }

    @Override
    public GameSet getObject() {
        GameSet gameSet = new GameSet();
        Group group = new Group();
        Round round = new Round();
        Tournament tournament = new Tournament();
        group.setTournament(tournament);
        round.setTournament(tournament);
        Encounter encounter = new Encounter();
        encounter.getGameSets().add(gameSet);
        encounter.setGroup(group);
        encounter.setRound(round);
        gameSet.setEncounter(encounter);
        encounterRepository.save(encounter);
        return gameSet;
    }

    @Override
    public GameSet changeDataField(GameSet gameSet) {
        gameSet.setSequenceNumber(9);
        return gameSet;
    }
}
