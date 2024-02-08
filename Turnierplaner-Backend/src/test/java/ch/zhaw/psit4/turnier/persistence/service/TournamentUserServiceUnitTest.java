package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.TournamentUser;
import ch.zhaw.psit4.turnier.model.User;
import ch.zhaw.psit4.turnier.persistence.repository.TournamentUserRepository;
import ch.zhaw.psit4.turnier.persistence.repository.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

public class TournamentUserServiceUnitTest extends BaseServiceUnitTest<TournamentUser, Integer> {

    @MockBean
    private TournamentUserRepository groupRepository;

    @Override
    public CrudRepository<TournamentUser, Integer> getCrudRepository() {
        return groupRepository;
    }

    @Override
    public ArrayList<TournamentUser> getObjList() {
        return new ArrayList<>(Collections.singleton(new TournamentUser()));
    }
}
