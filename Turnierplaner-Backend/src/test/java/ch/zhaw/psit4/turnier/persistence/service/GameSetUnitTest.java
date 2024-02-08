package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.GamePoints;
import ch.zhaw.psit4.turnier.model.GameSet;
import ch.zhaw.psit4.turnier.persistence.repository.GamePointRepository;
import ch.zhaw.psit4.turnier.persistence.repository.GameSetRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collections;

public class GameSetUnitTest extends BaseServiceUnitTest<GameSet, Integer> {

    @MockBean
    private GameSetRepository groupRepository;

    @Override
    public CrudRepository<GameSet, Integer> getCrudRepository() {
        return groupRepository;
    }

    @Override
    public ArrayList<GameSet> getObjList() {
        return new ArrayList<>(Collections.singleton(new GameSet()));
    }
}
