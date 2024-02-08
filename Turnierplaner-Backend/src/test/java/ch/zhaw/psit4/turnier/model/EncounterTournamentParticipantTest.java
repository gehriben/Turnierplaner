package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class EncounterTournamentParticipantTest extends BaseModelTest{

    private EncounterTournamentParticipant objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new EncounterTournamentParticipant();
    }

    @Test
    public void shouldGetAndSetEncounterTournamentParticipantId(){
        basicGetSetObjectTest(objectUnderTest::getEncounterTournamentParticipantId,objectUnderTest::setEncounterTournamentParticipantId,11);
    }

    @Test
    public void shouldGetAndSetEncounter(){
        basicGetSetObjectTest(objectUnderTest::getEncounter,objectUnderTest::setEncounter,mock(Encounter.class));
    }

    @Test
    public void shouldGetAndSetGamePoints(){
        basicGetSetSetTest(objectUnderTest::getGamePoints,objectUnderTest::setGamePoints,mock(GamePoints.class));
    }
}
