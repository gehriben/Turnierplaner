package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EncounterTournamentParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer encounterTournamentParticipantId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tournament_participant_id", nullable = false)
    private TournamentParticipant tournamentParticipant;


    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounter_id", nullable = false)
    private Encounter encounter;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "encounterTournamentParticipant")
    private Set<GamePoints> gamePoints = new HashSet<>();

    public EncounterTournamentParticipant() {
    }

    public EncounterTournamentParticipant(TournamentParticipant tournamentParticipant) {
        this.tournamentParticipant = tournamentParticipant;
    }
}
