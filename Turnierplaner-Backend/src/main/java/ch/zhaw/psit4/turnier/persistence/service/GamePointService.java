package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.GamePoints;
import ch.zhaw.psit4.turnier.persistence.repository.GamePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class GamePointService extends BaseService<GamePoints, Integer> {

    @Autowired
    private GamePointRepository gamePointRepository;

    @Override
    public CrudRepository getRepository() {
        return gamePointRepository;
    }
}
