package ch.zhaw.psit4.turnier.persistence.service;

import ch.zhaw.psit4.turnier.model.Ranking;
import ch.zhaw.psit4.turnier.model.RankingPlacement;
import ch.zhaw.psit4.turnier.model.Tournament;
import ch.zhaw.psit4.turnier.model.TournamentParticipant;
import ch.zhaw.psit4.turnier.persistence.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class RankingService extends BaseService<Ranking, Integer> {

    @Autowired
    private RankingRepository baseRepository;
    @Autowired
    private TournamentService tournamentService;

    @Override
    public CrudRepository getRepository() {
        return baseRepository;
    }

    public Ranking getTempRanking(Integer tournamentId) throws Exception {
        Ranking ranking = new Ranking();
        Tournament tournament = tournamentService.getObjectById(tournamentId);
        Map<TournamentParticipant, Long> resultMap = tournament.currentStats();
        List<RankingPlacement> rankingPlacements = new ArrayList<>();
        sortRanking(resultMap, rankingPlacements);
        calculateRank(ranking, rankingPlacements);
        ranking.getRankingPlacements().sort(Comparator.comparing(RankingPlacement::getRank));
        return ranking;
    }

    private void sortRanking(Map<TournamentParticipant, Long> resultMap, List<RankingPlacement> rankingPlacements) {
        for (Map.Entry<TournamentParticipant, Long> entry : resultMap.entrySet()) {
            rankingPlacements.add(new RankingPlacement(entry.getKey(), entry.getValue()));
        }
        rankingPlacements.sort(Comparator.comparing(RankingPlacement::getAmountWins).reversed());
    }

    private void calculateRank(Ranking ranking, List<RankingPlacement> rankingPlacements) {
        int rank = 0;
        long lastWins = -1;
        int buffer = 0;
        for (RankingPlacement rankingPlacement : rankingPlacements) {
            if (lastWins != rankingPlacement.getAmountWins()) {
                rank++;
                rank += buffer;
                buffer = 0;
            } else
                buffer++;

            lastWins = rankingPlacement.getAmountWins();
            rankingPlacement.setRank(rank);

            ranking.getRankingPlacements().add(rankingPlacement);
        }
    }
}
