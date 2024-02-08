package ch.zhaw.psit4.turnier;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.persistence.BaseIntegrationTest;

public class ParticipantIntegrationTest extends BaseIntegrationTest<Participant, Integer> {

    @Override
    public Integer getId(Participant Participant) {
        return Participant.getParticipantId();
    }

    @Override
    public Participant getObject() {
        Participant participant = new Participant();
        participant.setParticipantFirstName("Test");
        participant.setParticipantLastName("OMG");
        return participant;
    }

    @Override
    public Participant changeDataField(Participant participant) {
        participant.setParticipantFirstName("Test2");
        return participant;
    }


}
