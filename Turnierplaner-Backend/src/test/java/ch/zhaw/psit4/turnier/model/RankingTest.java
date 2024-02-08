package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class RankingTest extends BaseModelTest {

    private Ranking objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new Ranking();
    }

    @Test
    public void shouldGetAndSetGroupId(){
        basicGetSetObjectTest(objectUnderTest::getRankingId,objectUnderTest::setRankingId,12);
    }

    @Test
    public void shouldGetAndSetTournament(){
        basicGetSetObjectTest(objectUnderTest::getTournament,objectUnderTest::setTournament,mock(Tournament.class));
    }

    @Test
    public void shouldGetAndSetRankingPlacements(){
        basicGetSetSetTest(objectUnderTest::getRankingPlacements,objectUnderTest::setRankingPlacements,mock(RankingPlacement.class));
    }
}
