package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class TournamentParticipantTest extends BaseModelTest {

    private TournamentParticipant objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new TournamentParticipant();
    }

    @Test
    public void shouldGetAndSetTournamentParticipantId(){
        basicGetSetObjectTest(objectUnderTest::getTournamentParticipantId,objectUnderTest::setTournamentParticipantId,12);
    }

    @Test
    public void shouldGetAndSetParticipant(){
        basicGetSetObjectTest(objectUnderTest::getParticipant,objectUnderTest::setParticipant,mock(Participant.class));
    }

    @Test
    public void shouldGetAndSetGroup(){
        basicGetSetObjectTest(objectUnderTest::getGroup,objectUnderTest::setGroup,mock(Group.class));
    }

    @Test
    public void shouldGetAndSetEncounterTournamentParticipants(){
        basicGetSetSetTest(objectUnderTest::getEncounterTournamentParticipants,objectUnderTest::setEncounterTournamentParticipants,mock(EncounterTournamentParticipant.class));
    }

    @Test
    public void shouldGetAndSetRankingPlacements(){
        basicGetSetSetTest(objectUnderTest::getRankingPlacements,objectUnderTest::setRankingPlacements,mock(RankingPlacement.class));
    }
}
