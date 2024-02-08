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
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roundId;

    private boolean finished;

    private Integer roundNumber;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "round")
    private Set<Encounter> encounters = new HashSet<>();

    public Round() { }

    public Round(Tournament tournament, int roundNumber) {
        setTournament(tournament);
        setRoundNumber(roundNumber);
    }
}