package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Round;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.persistence.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RoundService extends BaseService<Round, Integer> {

    @Autowired
    private RoundRepository baseRepository;

    @Override
    public CrudRepository<Round, Integer> getRepository() {
        return baseRepository;
    }

    public Round getLatestRound(Tournament tournament) {
        Round latestRound = null;
        Iterable<Round> rounds = baseRepository.findAll();
        for (Round round : rounds) {
            if (round.getTournament().equals(tournament) &&
                    (latestRound == null || round.getRoundNumber() > latestRound.getRoundNumber())) {
                latestRound = round;
            }
        }
        return latestRound;
    }

    public Round createNextRound(Tournament tournament) {
        Round latestRound = getLatestRound(tournament);
        int newRoundNumber = getNewRoundNumber(latestRound);

        Round newRound = new Round();
        newRound.setTournament(tournament);
        newRound.setRoundNumber(newRoundNumber);

        return createObject(newRound);
    }

    private int getNewRoundNumber(Round latestRound) {
        int newRoundNumber = 1;
        if (latestRound != null) {
            newRoundNumber = latestRound.getRoundNumber() + 1;
        }
        return newRoundNumber;
    }
}
