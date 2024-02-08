package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.Group;
import ch.zhaw.psit4.turnier.model.Tournament;

public class GroupControllerTest extends BaseControllerTest<Group, Integer> {

    @Override
    public Integer getId(Group group) {
        return group.getGroupId();
    }

    @Override
    public Group getObject() {
        Group group = new Group();
        group.setTournament(new Tournament());
        return group;
    }

    @Override
    public Group changeDataField(Group group) {
        group.setGroupName("test");
        return group;
    }
}
