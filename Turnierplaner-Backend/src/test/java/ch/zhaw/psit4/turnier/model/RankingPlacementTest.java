package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class RankingPlacementTest extends BaseModelTest {
    private RankingPlacement objectUnderTest;
    private TournamentParticipant tournamentParticipantMock;


    @BeforeEach
    public void initEach(){
        objectUnderTest = new RankingPlacement();
        tournamentParticipantMock = mock(TournamentParticipant.class);
    }

    @Test
    public void shouldGetAndSetRankingPlacementId(){
        basicGetSetObjectTest(objectUnderTest::getRankingPlacementId,objectUnderTest::setRankingPlacementId,12);
    }

    @Test
    public void shouldGetAndSetRanking(){
        basicGetSetObjectTest(objectUnderTest::getRanking,objectUnderTest::setRanking,mock(Ranking.class));
    }

    @Test
    public void shouldGetAndSetTournamentParticipant(){
        basicGetSetObjectTest(objectUnderTest::getTournamentParticipant,objectUnderTest::setTournamentParticipant,mock(TournamentParticipant.class));
    }

    @Test
    public void shouldGetAndSetAmountWins(){
        assertEquals(0,objectUnderTest.getAmountWins());
        objectUnderTest.setAmountWins(11);
        assertEquals(11,objectUnderTest.getAmountWins());
    }

    @Test
    public void shouldGetAndSetRank(){
        assertEquals(0,objectUnderTest.getRank());
        objectUnderTest.setRank(10);
        assertEquals(10,objectUnderTest.getRank());
    }

    @Test
    public void constructorShouldSetValues(){
        objectUnderTest = new RankingPlacement(tournamentParticipantMock,1,2);
        assertEquals(2,objectUnderTest.getRank());
        assertEquals(1,objectUnderTest.getAmountWins());
        assertEquals(tournamentParticipantMock,objectUnderTest.getTournamentParticipant());
    }
}
