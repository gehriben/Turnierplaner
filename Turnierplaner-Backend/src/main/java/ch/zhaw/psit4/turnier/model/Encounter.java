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
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer encounterId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "encounter")
    private Set<EncounterTournamentParticipant> encounterTournamentParticipants = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "encounter")
    private Set<GameSet> gameSets = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "round_id", nullable = false)
    private Round round;

    public Encounter() {
    }

    public Encounter(List<TournamentParticipant> tournamentParticipantList) {
        tournamentParticipantList
                .stream()
                .map(EncounterTournamentParticipant::new)
                .peek(etp -> etp.setEncounter(this))
                .forEach(encounterTournamentParticipants::add);
        group = tournamentParticipantList.get(0).getGroup();
    }

    public Optional<TournamentParticipant> deliverWinner() {
        Map<Optional<TournamentParticipant>, Long> result = gameSets.stream()
                .collect(Collectors.groupingBy(GameSet::getWinner, Collectors.counting()));
        result.remove(Optional.empty());

        return result
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .map(Optional::get)
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encounter encounter = (Encounter) o;
        return com.google.common.base.Objects.equal(encounterId, encounter.encounterId);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(encounterId);
    }
}