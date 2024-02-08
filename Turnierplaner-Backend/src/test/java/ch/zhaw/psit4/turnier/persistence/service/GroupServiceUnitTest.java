package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Group;
import ch.zhaw.psit4.turnier.persistence.repository.GroupRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

public class GroupServiceUnitTest extends BaseServiceUnitTest<Group, Integer> {

    @MockBean
    private GroupRepository groupRepository;

    @Override
    public CrudRepository<Group, Integer> getCrudRepository() {
        return groupRepository;
    }

    @Override
    public ArrayList<Group> getObjList() {
        return new ArrayList<>(Collections.singleton(new Group()));
    }
}
