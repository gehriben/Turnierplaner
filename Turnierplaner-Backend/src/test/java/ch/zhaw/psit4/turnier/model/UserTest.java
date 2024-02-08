package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class UserTest extends BaseModelTest{

    private User objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new User();
    }

    @Test
    public void shouldGetAndSetUserId(){
        basicGetSetObjectTest(objectUnderTest::getUserId,objectUnderTest::setUserId,12);
    }

    @Test
    public void shouldGetAndSetUsername(){
        basicGetSetObjectTest(objectUnderTest::getUsername,objectUnderTest::setUsername,"Username");
    }

    @Test
    public void shouldGetAndSetPassword(){
        basicGetSetObjectTest(objectUnderTest::getPassword,objectUnderTest::setPassword,"Password123");
    }

    @Test
    public void shouldGetAndSetAdmin(){
        assertNull(objectUnderTest.getAdmin());
        objectUnderTest.setAdmin(true);
        assertEquals(true,objectUnderTest.getAdmin());
        objectUnderTest.setAdmin(false);
        assertEquals(false,objectUnderTest.getAdmin());
    }

    @Test
    public void shouldGetAndSetTournamentUser(){
        basicGetSetSetTest(objectUnderTest::getTournamentUser,objectUnderTest::setTournamentUser,mock(TournamentUser.class));
    }

    @Test
    public void shouldGetNoAuthorities(){
        assertNull(objectUnderTest.getAuthorities());
    }

    @Test
    public void accountShouldNotBeExpired(){
        assertTrue(objectUnderTest.isAccountNonExpired());
    }

    @Test
    public void accountShouldNotBeLocked(){
        assertTrue(objectUnderTest.isAccountNonLocked());
    }

    @Test
    public void credentialsShouldNotBeExpired(){
        assertTrue(objectUnderTest.isCredentialsNonExpired());
    }

    @Test
    public void shouldBeEnabled(){
        assertTrue(objectUnderTest.isEnabled());
    }
}
