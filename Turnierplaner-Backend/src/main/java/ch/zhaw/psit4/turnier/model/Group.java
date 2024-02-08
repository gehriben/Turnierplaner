package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tournament_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groupId;

    private String groupName = "New Group";

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private Set<TournamentParticipant> tournamentParticipant = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Encounter> encounters = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "tournament_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Tournament tournament;

    public Map<Optional<TournamentParticipant>, Long> currentStats() {
        return encounters.stream().collect(Collectors.groupingBy(Encounter::deliverWinner, Collectors.counting()));
    }
}