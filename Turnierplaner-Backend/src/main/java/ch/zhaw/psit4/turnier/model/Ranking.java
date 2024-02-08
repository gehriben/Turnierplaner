package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rankingId;

    @OneToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ranking")
    private List<RankingPlacement> rankingPlacements = new ArrayList<>();
}
