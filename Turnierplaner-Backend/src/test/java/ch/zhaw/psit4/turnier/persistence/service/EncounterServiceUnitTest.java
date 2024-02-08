package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Encounter;
import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.model.TournamentParticipant;
import ch.zhaw.psit4.turnier.persistence.repository.EncounterRepository;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class EncounterServiceUnitTest extends BaseServiceUnitTest<Encounter, Integer> {

    public static final int END_EXCLUSIVE_EVEN = 100;
    public static final int END_EXCLUSIVE_ODD = 99;
    public static final int END_EXCLUSIVE_ZERO = 0;
    @MockBean
    private EncounterRepository encounterRepository;
    @MockBean
    private TournamentService tournamentService;

    @Override
    public CrudRepository<Encounter, Integer> getCrudRepository() {
        return encounterRepository;
    }

    @Override
    public ArrayList<Encounter> getObjList() {
        return new ArrayList<>(Collections.singleton(new Encounter()));
    }

    @Test
    public void shouldCreateEncountersForEven() throws Exception {
        EncounterService encounterService = (EncounterService) baseService;
        mockGetTournamentById(END_EXCLUSIVE_EVEN);
        encounterService.createEncounters(anyInt(), anyInt());
        verify(getCrudRepository(), times(END_EXCLUSIVE_EVEN / 2)).save(any());
    }

    @Test
    public void shouldCreateEncountersForOdd() throws Exception {
        EncounterService encounterService = (EncounterService) baseService;
        mockGetTournamentById(END_EXCLUSIVE_ODD);
        encounterService.createEncounters(anyInt(), anyInt());
        // + 1, da für eine odd anz spieler ein freilos ohne gegner vergeben werden soll
        verify(getCrudRepository(), times(END_EXCLUSIVE_ODD / 2 + 1)).save(any());
    }

    @Test
    public void shouldCreateEncountersForZero() throws Exception {
        EncounterService encounterService = (EncounterService) baseService;
        mockGetTournamentById(END_EXCLUSIVE_ZERO);
        encounterService.createEncounters(anyInt(), anyInt());
        verify(getCrudRepository(), times(END_EXCLUSIVE_ZERO / 2)).save(any());
    }

    private void mockGetTournamentById(int endExclusive) throws Exception {
        Tournament tournament = new Tournament();
        IntStream.range(0, endExclusive)
                .mapToObj(i -> new TournamentParticipant())
                .peek(tP -> tP.setParticipant(new Participant()))
                .peek(tP -> tP.getParticipant().setParticipantFirstName(UUID.randomUUID().toString()))
                .peek(tP -> tP.setTournament(tournament))
                .forEach(tournament::addTournamentParticipant);
        when(tournamentService.getObjectById(any())).thenReturn(tournament);
    }
}
