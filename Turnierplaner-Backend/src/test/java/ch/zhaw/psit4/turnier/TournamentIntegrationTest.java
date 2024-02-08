package ch.zhaw.psit4.turnier;

import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.persistence.BaseIntegrationTest;

public class TournamentIntegrationTest extends BaseIntegrationTest<Tournament, Integer> {
    @Override
    public Integer getId(Tournament tournament) {
        return tournament.getTournamentId();
    }

    @Override
    public Tournament getObject() {
        Tournament tournament = new Tournament();
        tournament.setTournamentName("Test");
        return tournament;
    }

    @Override
    public Tournament changeDataField(Tournament tournament) {
        tournament.setTournamentName("Test2");
        return tournament;
    }


}
