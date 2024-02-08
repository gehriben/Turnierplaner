package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.Tournament;

public class TournamentControllerTest extends BaseControllerTest<Tournament, Integer> {

    @Override
    public Integer getId(Tournament tournament) {
        return tournament.getTournamentId();
    }

    @Override
    public Tournament getObject() {
        return new Tournament();
    }

    @Override
    public Tournament changeDataField(Tournament tournament) {
        tournament.setTournamentName("Test");
        return tournament;
    }
}
