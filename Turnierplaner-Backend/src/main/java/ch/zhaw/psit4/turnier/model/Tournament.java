package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;


@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tournamentId;

    private String tournamentName;

    private Date tournamentDate;

    /**
     * NEW:       Tournament is created, configuration done, but it has not been started, yet.
     * STARTED:     Tournament has encounters.
     * FINISHED:    Tournament is finished, ranking exists and no more games are played.
     */
    private TournamentState tournamentState = TournamentState.NEW;

    private GroupDefinition groupDefinition;

    private Integer groupDivider;

    private TournamentSystem tournamentSystem = TournamentSystem.SWISS_SYSTEM;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
    private Set<TournamentParticipant> tournamentParticipant = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
    private Set<Round> rounds = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
    private Set<Group> groups = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
    private Set<TournamentUser> tournamentUsers = new HashSet<>();

    public void addTournamentParticipant(TournamentParticipant tP) {
        tournamentParticipant.add(tP);
    }

    public Map<TournamentParticipant, Long> currentStats() {
        Map<Optional<TournamentParticipant>, Long> tpOptionalWinMap = groups.stream()
                .map(Group::getEncounters)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Encounter::deliverWinner, Collectors.counting()));
        Map<TournamentParticipant,Long> tpWinMap = new HashMap<>();
        for (Map.Entry<Optional<TournamentParticipant>, Long> entry : tpOptionalWinMap.entrySet()) {
            if(!entry.getKey().isPresent())
                continue;
            tpWinMap.put(entry.getKey().get(), entry.getValue());
        }
        tournamentParticipant.stream().filter(tp->!tpWinMap.containsKey(tp)).forEach(tp->tpWinMap.put(tp,0L));
        return tpWinMap;
    }
}
