package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.base.Objects;

public class GamePointsTest extends BaseModelTest{

    private GamePoints objectUnderTest;
    private GameSet gameSetMock1;
    private GameSet gameSetMock2;
    private EncounterTournamentParticipant participantMock1;
    private EncounterTournamentParticipant participantMock2;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new GamePoints();
        gameSetMock1 = mock(GameSet.class);
        gameSetMock2 = mock(GameSet.class);
        participantMock1 = mock(EncounterTournamentParticipant.class);
        participantMock2 = mock(EncounterTournamentParticipant.class);
    }

    @Test
    public void shouldGetAndSetGamePointsId(){
        basicGetSetObjectTest(objectUnderTest::getGamePointsId,objectUnderTest::setGamePointsId,11);
    }

    @Test
    public void shouldGetAndSetEncounterTournamentParticipant(){
        basicGetSetObjectTest(objectUnderTest::getEncounterTournamentParticipant,objectUnderTest::setEncounterTournamentParticipant,mock(EncounterTournamentParticipant.class));
    }

    @Test
    public void shouldGetAndSetGameSet(){
        basicGetSetObjectTest(objectUnderTest::getGameSet,objectUnderTest::setGameSet,mock(GameSet.class));
    }

    @Test
    public void shouldGetAndSetPoints(){
        assertEquals(0,objectUnderTest.getPoints());
        objectUnderTest.setPoints(11);
        assertEquals(11,objectUnderTest.getPoints());
    }

    @Test
    public void equalsShouldReturnFalse(){
        assertFalse(objectUnderTest.equals(null));
        assertFalse(objectUnderTest.equals("anyString"));

        int anyInt = 11;
        assertFalse(objectUnderTest.equals(anyInt));

        when(gameSetMock1.getSetId()).thenReturn(1);
        when(gameSetMock2.getSetId()).thenReturn(2);
        when(participantMock1.getEncounterTournamentParticipantId()).thenReturn(1);
        when(participantMock1.getEncounterTournamentParticipantId()).thenReturn(2);


        objectUnderTest.setGameSet(gameSetMock1);
        objectUnderTest.setEncounterTournamentParticipant(participantMock1);
        GamePoints gamePoints = new GamePoints();
        gamePoints.setGameSet(gameSetMock2);
        gamePoints.setEncounterTournamentParticipant(participantMock2);

        assertFalse(objectUnderTest.equals(gamePoints));
        when(gameSetMock2.getSetId()).thenReturn(1);
        assertFalse(objectUnderTest.equals(gamePoints));
        when(gameSetMock2.getSetId()).thenReturn(2);
        when(participantMock1.getEncounterTournamentParticipantId()).thenReturn(2);
        assertFalse(objectUnderTest.equals(gamePoints));
    }

    @Test
    public void equalsShouldReturnTrue(){
        when(gameSetMock1.getSetId()).thenReturn(1);
        when(gameSetMock2.getSetId()).thenReturn(1);
        when(participantMock1.getEncounterTournamentParticipantId()).thenReturn(1);
        when(participantMock2.getEncounterTournamentParticipantId()).thenReturn(1);

        GamePoints gamePoints = new GamePoints();
        objectUnderTest.setGameSet(gameSetMock1);
        objectUnderTest.setEncounterTournamentParticipant(participantMock1);
        gamePoints.setGameSet(gameSetMock2);
        gamePoints.setEncounterTournamentParticipant(participantMock2);

        assertTrue(objectUnderTest.equals(gamePoints));
    }

    @Test
    public void hashShouldBeTheSame(){
        when(gameSetMock1.getSetId()).thenReturn(11);
        when(participantMock1.getEncounterTournamentParticipantId()).thenReturn(21);
        objectUnderTest.setGameSet(gameSetMock1);
        objectUnderTest.setEncounterTournamentParticipant(participantMock1);

        assertEquals(Objects.hashCode(21,11),objectUnderTest.hashCode());
    }
}
