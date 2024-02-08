package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.persistence.repository.TournamentRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;

public class TournamentServiceUnitTest extends BaseServiceUnitTest<Tournament, Integer> {

    @MockBean
    private TournamentRepository tournamentRepository;

    @Override
    public CrudRepository<Tournament, Integer> getCrudRepository() {
        return tournamentRepository;
    }

    @Override
    public ArrayList<Tournament> getObjList() {
        return new ArrayList<>(Collections.singleton(new Tournament()));
    }

    @Override
    public void shouldReturnAllFromRepo() {
        ArrayList<Tournament> Ts = getObjList();
        when(tournamentRepository.OrderByTournamentDateDesc()).thenReturn(Ts);
        super.shouldReturnAllFromRepo();
    }
}
