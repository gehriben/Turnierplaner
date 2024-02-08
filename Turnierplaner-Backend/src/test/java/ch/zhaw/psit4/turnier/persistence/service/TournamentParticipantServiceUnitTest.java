package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.model.TournamentParticipant;
import ch.zhaw.psit4.turnier.persistence.repository.TournamentParticipantRepository;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TournamentParticipantServiceUnitTest extends BaseServiceUnitTest<TournamentParticipant, Integer> {

    @MockBean
    private TournamentParticipantRepository tournamentParticipantRepository;
    @MockBean
    private TournamentService tournamentService;

    @Override
    public CrudRepository<TournamentParticipant, Integer> getCrudRepository() {
        return tournamentParticipantRepository;
    }

    @Override
    public ArrayList<TournamentParticipant> getObjList() {
        return new ArrayList<>(Collections.singleton(new TournamentParticipant()));
    }

    @Test
    public void shouldCreateTournamentParticipant() throws Exception {
        TournamentParticipantService tournamentParticipantService = (TournamentParticipantService) baseService;
        when(tournamentService.getObjectById(any())).thenReturn(new Tournament());
        Participant participantNew = new Participant();
        TournamentParticipant tournamentParticipant = new TournamentParticipant();
        tournamentParticipant.setParticipant(participantNew);
        when(tournamentParticipantRepository.save(any())).thenReturn(tournamentParticipant);
        Participant participant = tournamentParticipantService.createTournamentParticipant(participantNew, 1);
        assertEquals(participant, participantNew);
        verify(tournamentParticipantRepository, times(1)).save(any());
    }
}
