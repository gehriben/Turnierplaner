package ch.zhaw.psit4.turnier;

import ch.zhaw.psit4.turnier.model.Group;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.persistence.BaseIntegrationTest;

public class GroupIntegrationTest extends BaseIntegrationTest<Group, Integer> {
    @Override
    public Integer getId(Group Group) {
        return Group.getGroupId();
    }

    @Override
    public Group getObject() {
        Group group = new Group();
        group.setTournament(new Tournament());
        group.setGroupName("Test");
        return group;
    }

    @Override
    public Group changeDataField(Group group) {
        group.setGroupName("Test2");
        return group;
    }

}
