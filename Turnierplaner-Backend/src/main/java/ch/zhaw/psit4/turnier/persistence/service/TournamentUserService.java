package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.TournamentUser;
import ch.zhaw.psit4.turnier.persistence.repository.TournamentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TournamentUserService extends BaseService<TournamentUser, Integer> {

    @Autowired
    private TournamentUserRepository baseRepository;

    @Override
    public CrudRepository<TournamentUser, Integer> getRepository() {
        return baseRepository;
    }
}
