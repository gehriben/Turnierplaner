package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.*;

public class EncounterControllerTest extends BaseControllerTest<Encounter, Integer> {

    @Override
    public Integer getId(Encounter encounter) {
        return encounter.getEncounterId();
    }

    @Override
    public Encounter getObject() {
        Encounter encounter = new Encounter();
        Tournament tournament = new Tournament();
        Round round = new Round();
        round.setTournament(tournament);
        Group group = new Group();
        group.setTournament(new Tournament());
        encounter.setGroup(group);
        encounter.setRound(round);
        return encounter;
    }

    @Override
    public Encounter changeDataField(Encounter encounter) {
        GameSet gameSet = new GameSet();
        gameSet.setEncounter(encounter);
        encounter.getGameSets().add(gameSet);
        return encounter;
    }
}
