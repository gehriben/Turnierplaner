package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Group;
import ch.zhaw.psit4.turnier.model.User;
import ch.zhaw.psit4.turnier.persistence.repository.GroupRepository;
import ch.zhaw.psit4.turnier.persistence.repository.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

public class UserServiceUnitTest extends BaseServiceUnitTest<User, Integer> {

    @MockBean
    private UserRepository groupRepository;

    @Override
    public CrudRepository<User, Integer> getCrudRepository() {
        return groupRepository;
    }

    @Override
    public ArrayList<User> getObjList() {
        return new ArrayList<>(Collections.singleton(new User()));
    }
}
