package ch.zhaw.psit4.turnier;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.model.TournamentParticipant;
import ch.zhaw.psit4.turnier.persistence.BaseIntegrationTest;
import ch.zhaw.psit4.turnier.persistence.service.TournamentService;
import org.springframework.boot.test.mock.mockito.MockBean;

public class TournamentParticipantIntegrationTest extends BaseIntegrationTest<TournamentParticipant, Integer> {

    @MockBean
    public TournamentService tournamentService;

    @Override
    public Integer getId(TournamentParticipant TournamentParticipant) {
        return TournamentParticipant.getTournamentParticipantId();
    }

    @Override
    public TournamentParticipant getObject() {
        TournamentParticipant TournamentParticipant = new TournamentParticipant();
        TournamentParticipant.setTournament(new Tournament());
        TournamentParticipant.setParticipant(new Participant());
        return TournamentParticipant;
    }

    @Override
    public TournamentParticipant changeDataField(TournamentParticipant TournamentParticipant) {
        Tournament t = new Tournament();
        t.setTournamentName("Döner");
        TournamentParticipant.setTournament(t);
        return TournamentParticipant;
    }


}
