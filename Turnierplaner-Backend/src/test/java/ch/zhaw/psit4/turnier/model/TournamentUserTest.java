package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class TournamentUserTest extends BaseModelTest {

    private TournamentUser objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new TournamentUser();
    }

    @Test
    public void shouldGetAndSetTournamentUserId(){
        basicGetSetObjectTest(objectUnderTest::getTournamentUserId,objectUnderTest::setTournamentUserId,12);
    }

    @Test
    public void shouldGetAndSetTournament(){
        basicGetSetObjectTest(objectUnderTest::getTournament,objectUnderTest::setTournament,mock(Tournament.class));
    }

    @Test
    public void shouldGetAndSetUser(){
        basicGetSetObjectTest(objectUnderTest::getUser,objectUnderTest::setUser,mock(User.class));
    }

    @Test
    public void shouldGetAndSetRole(){
        assertEquals(TournamentUserRole.PLAYER,objectUnderTest.getRole());
        objectUnderTest.setRole(TournamentUserRole.REFEREE);
        assertEquals(TournamentUserRole.REFEREE,objectUnderTest.getRole());
        objectUnderTest.setRole(TournamentUserRole.MANAGER);
        assertEquals(TournamentUserRole.MANAGER,objectUnderTest.getRole());
    }
}
