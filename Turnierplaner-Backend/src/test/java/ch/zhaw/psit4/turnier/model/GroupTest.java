package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GroupTest extends BaseModelTest {

    private Group objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new Group();
    }

    @Test
    public void shouldGetAndSetGroupId(){
        basicGetSetObjectTest(objectUnderTest::getGroupId,objectUnderTest::setGroupId,11);
    }

    @Test
    public void shouldGetAndSetGroupName(){
        assertEquals("New Group",objectUnderTest.getGroupName());
        objectUnderTest.setGroupName("NewGroupName");
        assertEquals("NewGroupName",objectUnderTest.getGroupName());
    }

    @Test
    public void shouldGetAndSetTournamentParticipants(){
        basicGetSetSetTest(objectUnderTest::getTournamentParticipant,objectUnderTest::setTournamentParticipant,mock(TournamentParticipant.class));
    }

    @Test
    public void shouldGetAndSetEncounters(){
        basicGetSetSetTest(objectUnderTest::getEncounters,objectUnderTest::setEncounters,mock(Encounter.class));
    }

    @Test
    public void shouldGetAndSetTournament(){
        basicGetSetObjectTest(objectUnderTest::getTournament,objectUnderTest::setTournament,mock(Tournament.class));
    }

    @Test
    public void shouldGetCurrentStats(){
        Set<Encounter> encounterSet = new HashSet<>();
        objectUnderTest.setEncounters(encounterSet);
        Map<Optional<TournamentParticipant>,Long> expectedMap = new HashMap<>();
        assertEquals(expectedMap,objectUnderTest.currentStats());

        Encounter encounterMock1 = mock(Encounter.class);
        Encounter encounterMock2 = mock(Encounter.class);
        encounterSet.add(encounterMock1);
        encounterSet.add(encounterMock2);

        Optional<TournamentParticipant> expectedWinner1 = Optional.of(mock(TournamentParticipant.class));
        Optional<TournamentParticipant> expectedWinner2 = Optional.of(mock(TournamentParticipant.class));

        when(encounterMock1.deliverWinner()).thenReturn(expectedWinner1);
        when(encounterMock2.deliverWinner()).thenReturn(expectedWinner2);


        expectedMap.put(expectedWinner1, new Long("1"));
        expectedMap.put(expectedWinner2, new Long("1"));

        assertEquals(expectedMap,objectUnderTest.currentStats());
    }
}
