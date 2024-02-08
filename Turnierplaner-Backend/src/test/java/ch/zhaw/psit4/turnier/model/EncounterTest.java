package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EncounterTest extends BaseModelTest{

    private Encounter objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new Encounter();
    }

    @Test
    public void shouldGetAndSetEncounterId(){
        basicGetSetObjectTest(objectUnderTest::getEncounterId,objectUnderTest::setEncounterId,12);
    }

    @Test
    public void shouldGetAndSetEncounterTournamentParticipants(){
        basicGetSetSetTest(objectUnderTest::getEncounterTournamentParticipants,objectUnderTest::setEncounterTournamentParticipants,mock(EncounterTournamentParticipant.class));
    }

    @Test
    public void shouldGetAndSetGameSets(){
        basicGetSetSetTest(objectUnderTest::getGameSets,objectUnderTest::setGameSets,mock(GameSet.class));
    }

    @Test
    public void shouldGetAndSetGroup() {
        basicGetSetObjectTest(objectUnderTest::getGroup,objectUnderTest::setGroup,mock(Group.class));
    }

    @Test
    public void shouldDeliverWinner(){
        GameSet gameSetMock = mock(GameSet.class);
        TournamentParticipant winnerParticipantMock = mock(TournamentParticipant.class);
        Optional<TournamentParticipant> expectedWinner = Optional.of(winnerParticipantMock);
        Set<GameSet> gameSets = new HashSet<>();
        gameSets.add(gameSetMock);
        objectUnderTest.setGameSets(gameSets);

        when(gameSetMock.getWinner()).thenReturn(expectedWinner);

        assertEquals(expectedWinner,objectUnderTest.deliverWinner());
    }

    @Test
    public void shouldDeliverNoWinner(){
        GameSet gameSetMock = mock(GameSet.class);
        Optional<TournamentParticipant> expectedWinner = Optional.empty();
        Set<GameSet> gameSets = new HashSet<>();
        gameSets.add(gameSetMock);
        objectUnderTest.setGameSets(gameSets);

        when(gameSetMock.getWinner()).thenReturn(expectedWinner);

        assertEquals(expectedWinner,objectUnderTest.deliverWinner());
    }
}
