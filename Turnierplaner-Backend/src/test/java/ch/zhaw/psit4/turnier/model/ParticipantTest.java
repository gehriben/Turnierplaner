package ch.zhaw.psit4.turnier.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class ParticipantTest extends BaseModelTest {

    private Participant objectUnderTest;

    @BeforeEach
    public void initEach(){
        objectUnderTest = new Participant();
    }

    @Test
    public void shouldGetAndSetParticipantId(){
        basicGetSetObjectTest(objectUnderTest::getParticipantId,objectUnderTest::setParticipantId,11);
    }

    @Test
    public void shouldGetAndSetParticipantFirstName(){
        basicGetSetObjectTest(objectUnderTest::getParticipantFirstName,objectUnderTest::setParticipantFirstName,"FristName");
    }

    @Test
    public void shouldGetAndSetParticipantLastName(){
        basicGetSetObjectTest(objectUnderTest::getParticipantLastName,objectUnderTest::setParticipantLastName,"LastName");
    }

    @Test
    public void shouldGetAndSetParticipantResidence(){
        basicGetSetObjectTest(objectUnderTest::getParticipantResidence,objectUnderTest::setParticipantResidence,"ResidenceName");
    }

    @Test
    public void shouldGetAndSetParticipantZipCode(){
        basicGetSetObjectTest(objectUnderTest::getParticipantZipCode,objectUnderTest::setParticipantZipCode,"6789");
    }

    @Test
    public void shouldGetAndSetParticipantDateOfBirth(){
        basicGetSetObjectTest(objectUnderTest::getParticipantDateOfBirth,objectUnderTest::setParticipantDateOfBirth,new Date(Calendar.getInstance().getTime().getTime()));
    }

    @Test
    public void shouldGetAndSetTournamentParticipants(){
        basicGetSetSetTest(objectUnderTest::getTournamentParticipant,objectUnderTest::setTournamentParticipant,mock(TournamentParticipant.class));
    }
}
