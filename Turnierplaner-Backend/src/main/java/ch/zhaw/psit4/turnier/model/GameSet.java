package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GameSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer setId;

    /**
     * The sequence number identifies which position the set is.
     * The sequence number is unique per match.
     * E.g. The first played set in a game has the sequence number 1.
     */
    private int sequenceNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gameSet")
    private Set<GamePoints> gamePoints = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "encounter_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Encounter encounter;

    public Optional<TournamentParticipant> getWinner() {
        List<GamePoints> gamePointsSorted = gamePoints.stream()
                .sorted(Comparator.comparing(GamePoints::getPoints).reversed())
                .collect(Collectors.toList());
        if (gamePointsSorted.size() == 0)
            return Optional.empty();
        if (gamePointsSorted.size() == 1)
            return Optional.ofNullable(gamePointsSorted.get(0).getEncounterTournamentParticipant().getTournamentParticipant());
        if (gamePointsSorted.get(0).getPoints() == gamePointsSorted.get(1).getPoints())
            return Optional.empty();
        return Optional.ofNullable(gamePointsSorted.get(0).getEncounterTournamentParticipant().getTournamentParticipant());
    }
}
