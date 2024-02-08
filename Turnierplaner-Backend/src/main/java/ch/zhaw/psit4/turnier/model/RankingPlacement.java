package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RankingPlacement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rankingPlacementId;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "ranking_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Ranking ranking;

    @JoinColumn(name = "tournament_participant_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private TournamentParticipant tournamentParticipant;

    @Transient
    private long amountWins;

    private int rank;

    public RankingPlacement(TournamentParticipant tournamentParticipant, long amountWins, int rank) {
        this.tournamentParticipant = tournamentParticipant;
        this.amountWins = amountWins;
        this.rank = rank;
    }

    public RankingPlacement(TournamentParticipant tournamentParticipant, long amountWins) {
        this.tournamentParticipant = tournamentParticipant;
        this.amountWins = amountWins;
    }

    public RankingPlacement() {
    }
}
