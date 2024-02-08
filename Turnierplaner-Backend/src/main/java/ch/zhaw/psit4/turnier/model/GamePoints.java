package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GamePoints {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gamePointsId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "encounterParticipant_id", nullable = false)
    private EncounterTournamentParticipant encounterTournamentParticipant;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "gameSet_id", nullable = false)
    private GameSet gameSet;

    private int points;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePoints that = (GamePoints) o;
        return Objects.equal(encounterTournamentParticipant.getEncounterTournamentParticipantId(), that.encounterTournamentParticipant.getEncounterTournamentParticipantId()) &&
                Objects.equal(gameSet.getSetId(), that.gameSet.getSetId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(encounterTournamentParticipant.getEncounterTournamentParticipantId(), gameSet.getSetId());
    }
}
