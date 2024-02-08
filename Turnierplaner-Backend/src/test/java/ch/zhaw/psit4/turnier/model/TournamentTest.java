package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TournamentTest extends BaseModelTest {

    private Tournament objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new Tournament();
    }

    @Test
    public void shouldGetAndSetTournamentId(){
        basicGetSetObjectTest(objectUnderTest::getTournamentId,objectUnderTest::setTournamentId,12);
    }

    @Test
    public void shouldGetAndSetTournamentName(){
        basicGetSetObjectTest(objectUnderTest::getTournamentName,objectUnderTest::setTournamentName,"TournamentName");
    }

    @Test
    public void shouldGetAndSetTournamentDate(){
        basicGetSetObjectTest(objectUnderTest::getTournamentDate,objectUnderTest::setTournamentDate,new Date(Calendar.getInstance().getTime().getTime()));
    }

    @Test
    public void shouldGetAndSetTournamentState(){
        assertEquals(TournamentState.NEW,objectUnderTest.getTournamentState());
        objectUnderTest.setTournamentState(TournamentState.STARTED);
        assertEquals(TournamentState.STARTED,objectUnderTest.getTournamentState());
        objectUnderTest.setTournamentState(TournamentState.FINISHED);
        assertEquals(TournamentState.FINISHED,objectUnderTest.getTournamentState());
    }

    @Test
    public void shouldGetAndSetGroupDefinition(){
        assertNull(objectUnderTest.getGroupDefinition());
        objectUnderTest.setGroupDefinition(GroupDefinition.GROUP_SIZE);
        assertEquals(GroupDefinition.GROUP_SIZE,objectUnderTest.getGroupDefinition());
        objectUnderTest.setGroupDefinition(GroupDefinition.AMOUNT_GROUPS);
        assertEquals(GroupDefinition.AMOUNT_GROUPS,objectUnderTest.getGroupDefinition());
    }

    @Test
    public void shouldGetAndSeGroupDivider(){
        assertEquals(2,objectUnderTest.getGroupDivider());
        objectUnderTest.setGroupDivider(4);
        assertEquals(4,objectUnderTest.getGroupDivider());
    }

    @Test
    public void shouldGetAndSetTournamentSystem(){
        assertEquals(TournamentSystem.SWISS_SYSTEM,objectUnderTest.getTournamentSystem());
        objectUnderTest.setTournamentSystem(TournamentSystem.KO_SYSTEM);
        assertEquals(TournamentSystem.KO_SYSTEM,objectUnderTest.getTournamentSystem());
    }

    @Test
    public void shouldGetAndSetTournamentParticipants(){
        basicGetSetSetTest(objectUnderTest::getTournamentParticipant,objectUnderTest::setTournamentParticipant,mock(TournamentParticipant.class));
    }

    @Test
    public void shouldGetAndSetGroups(){
        basicGetSetSetTest(objectUnderTest::getGroups,objectUnderTest::setGroups,mock(Group.class));
    }

    @Test
    public void shouldGetAndSetTournamentUsers(){
        basicGetSetSetTest(objectUnderTest::getTournamentUsers,objectUnderTest::setTournamentUsers,mock(TournamentUser.class));
    }

    @Test
    public void shouldAddTournamentParticipant(){
        basicGetSetSetTest(objectUnderTest::getTournamentParticipant,objectUnderTest::setTournamentParticipant,mock(TournamentParticipant.class));
        TournamentParticipant tournamentParticipantMock = mock(TournamentParticipant.class);
        objectUnderTest.addTournamentParticipant(tournamentParticipantMock);
        assertTrue(objectUnderTest.getTournamentParticipant().contains(tournamentParticipantMock));
    }

    @Test
    public void shouldGetCurrentStats(){
        Set<Group> groupSet = new HashSet<>();
        objectUnderTest.setGroups(groupSet);
        Map<Optional<TournamentParticipant>,Long> expectedMap = new HashMap<>();
        assertEquals(expectedMap,objectUnderTest.currentStats());

        Group groupMock1 = mock(Group.class);
        Group groupMock2 = mock(Group.class);
        groupSet.add(groupMock1);
        groupSet.add(groupMock2);

        Set<Encounter> encounterSet1 = new HashSet<>();
        Set<Encounter> encounterSet2 = new HashSet<>();

        when(groupMock1.getEncounters()).thenReturn(encounterSet1);
        when(groupMock2.getEncounters()).thenReturn(encounterSet2);

        Encounter encounterMock1 = mock(Encounter.class);
        Encounter encounterMock2 = mock(Encounter.class);
        Encounter encounterMock3 = mock(Encounter.class);
        Encounter encounterMock4 = mock(Encounter.class);
        encounterSet1.add(encounterMock1);
        encounterSet1.add(encounterMock2);
        encounterSet2.add(encounterMock3);
        encounterSet2.add(encounterMock4);

        Optional<TournamentParticipant> expectedWinner1 = Optional.of(mock(TournamentParticipant.class));
        Optional<TournamentParticipant> expectedWinner2 = Optional.of(mock(TournamentParticipant.class));
        Optional<TournamentParticipant> expectedWinner3 = Optional.of(mock(TournamentParticipant.class));
        Optional<TournamentParticipant> expectedWinner4 = Optional.of(mock(TournamentParticipant.class));

        when(encounterMock1.deliverWinner()).thenReturn(expectedWinner1);
        when(encounterMock2.deliverWinner()).thenReturn(expectedWinner2);
        when(encounterMock3.deliverWinner()).thenReturn(expectedWinner3);
        when(encounterMock4.deliverWinner()).thenReturn(expectedWinner4);


        expectedMap.put(expectedWinner1, new Long("1"));
        expectedMap.put(expectedWinner2, new Long("1"));
        expectedMap.put(expectedWinner3, new Long("1"));
        expectedMap.put(expectedWinner4, new Long("1"));

        assertEquals(expectedMap,objectUnderTest.currentStats());
    }
}
