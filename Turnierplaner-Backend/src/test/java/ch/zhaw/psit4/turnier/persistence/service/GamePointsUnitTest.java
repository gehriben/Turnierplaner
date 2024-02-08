package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.GamePoints;
import ch.zhaw.psit4.turnier.model.User;
import ch.zhaw.psit4.turnier.persistence.repository.GamePointRepository;
import ch.zhaw.psit4.turnier.persistence.repository.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

public class GamePointsUnitTest extends BaseServiceUnitTest<GamePoints, Integer> {

    @MockBean
    private GamePointRepository groupRepository;

    @Override
    public CrudRepository<GamePoints, Integer> getCrudRepository() {
        return groupRepository;
    }

    @Override
    public ArrayList<GamePoints> getObjList() {
        return new ArrayList<>(Collections.singleton(new GamePoints()));
    }
}
