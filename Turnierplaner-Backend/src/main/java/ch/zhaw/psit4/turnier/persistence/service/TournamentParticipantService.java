package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Participant;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.model.TournamentParticipant;
import ch.zhaw.psit4.turnier.persistence.repository.TournamentParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TournamentParticipantService extends BaseService<TournamentParticipant, Integer> {

    @Autowired
    private TournamentParticipantRepository baseRepository;

    @Autowired
    private TournamentService tournamentService;

    @Override
    public CrudRepository getRepository() {
        return baseRepository;
    }

    public Participant createTournamentParticipant(Participant participant, Integer tournamentId) throws Exception {
        Tournament tournament = tournamentService.getObjectById(tournamentId);
        TournamentParticipant tournamentParticipant = new TournamentParticipant();
        tournamentParticipant.setParticipant(participant);
        tournamentParticipant.setTournament(tournament);
        return createObject(tournamentParticipant).getParticipant();
    }
}
