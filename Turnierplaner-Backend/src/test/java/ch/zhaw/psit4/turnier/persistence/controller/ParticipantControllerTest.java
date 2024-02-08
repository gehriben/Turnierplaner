package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.model.Tournament;

public class ParticipantControllerTest extends BaseControllerTest<Participant, Integer> {

    @Override
    public Integer getId(Participant participant) {
        return participant.getParticipantId();
    }

    @Override
    public Participant getObject() {
        return new Participant();
    }

    @Override
    public Participant changeDataField(Participant participant) {
        participant.setParticipantFirstName("Test");
        return participant;
    }
}
