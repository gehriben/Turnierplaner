package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GameSetTest extends BaseModelTest {

    private GameSet objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new GameSet();
    }

    @Test
    public void shouldGetAndSetSetId(){
        basicGetSetObjectTest(objectUnderTest::getSetId,objectUnderTest::setSetId,12);
    }

    @Test
    public void shouldGetAndSetGamePoints(){
        basicGetSetSetTest(objectUnderTest::getGamePoints,objectUnderTest::setGamePoints,mock(GamePoints.class));
    }

    @Test
    public void shouldGetAndSetEncounter(){
        basicGetSetObjectTest(objectUnderTest::getEncounter,objectUnderTest::setEncounter,mock(Encounter.class));
    }

    @Test
    public void shouldGetWinner(){
        Set<GamePoints> gamePoints = new HashSet<>();
        GamePoints gamePointsMockWinner = mock(GamePoints.class);
        GamePoints gamePointsMockLoser = mock(GamePoints.class);
        gamePoints.add(gamePointsMockWinner);
        gamePoints.add(gamePointsMockLoser);
        objectUnderTest.setGamePoints(gamePoints);

        when(gamePointsMockWinner.getPoints()).thenReturn(5);
        when(gamePointsMockLoser.getPoints()).thenReturn(3);

        EncounterTournamentParticipant eTPWinnerMock = mock(EncounterTournamentParticipant.class);
        when(gamePointsMockWinner.getEncounterTournamentParticipant()).thenReturn(eTPWinnerMock);
        TournamentParticipant tPWinnerMock = mock(TournamentParticipant.class);
        when(eTPWinnerMock.getTournamentParticipant()).thenReturn(tPWinnerMock);

        EncounterTournamentParticipant eTPLoserMock = mock(EncounterTournamentParticipant.class);
        when(gamePointsMockLoser.getEncounterTournamentParticipant()).thenReturn(eTPLoserMock);
        TournamentParticipant tPLoserMock = mock(TournamentParticipant.class);
        when(eTPLoserMock.getTournamentParticipant()).thenReturn(tPLoserMock);

        Optional<TournamentParticipant> expectedWinner = Optional.of(tPWinnerMock);
        assertEquals(expectedWinner,objectUnderTest.getWinner());
    }

    @Test
    public void shouldGetNoWinner(){
        Set<GamePoints> gamePoints = new HashSet<>();
        objectUnderTest.setGamePoints(gamePoints);
        Optional<TournamentParticipant> expectedWinner = Optional.empty();
        assertEquals(expectedWinner,objectUnderTest.getWinner());

        GamePoints gamePointsMockWinner = mock(GamePoints.class);
        GamePoints gamePointsMockLoser = mock(GamePoints.class);
        gamePoints.add(gamePointsMockWinner);
        gamePoints.add(gamePointsMockLoser);


        when(gamePointsMockWinner.getPoints()).thenReturn(3);
        when(gamePointsMockLoser.getPoints()).thenReturn(3);

        EncounterTournamentParticipant eTPWinnerMock = mock(EncounterTournamentParticipant.class);
        when(gamePointsMockWinner.getEncounterTournamentParticipant()).thenReturn(eTPWinnerMock);
        TournamentParticipant tPWinnerMock = mock(TournamentParticipant.class);
        when(eTPWinnerMock.getTournamentParticipant()).thenReturn(tPWinnerMock);

        EncounterTournamentParticipant eTPLoserMock = mock(EncounterTournamentParticipant.class);
        when(gamePointsMockLoser.getEncounterTournamentParticipant()).thenReturn(eTPLoserMock);
        TournamentParticipant tPLoserMock = mock(TournamentParticipant.class);
        when(eTPLoserMock.getTournamentParticipant()).thenReturn(tPLoserMock);

        assertEquals(expectedWinner,objectUnderTest.getWinner());
    }
}
