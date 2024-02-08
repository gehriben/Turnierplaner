package ch.zhaw.psit4.turnier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer participantId;

    private String participantFirstName;

    private String participantLastName;

    private String participantResidence;

    private String participantZipCode;

    private Date participantDateOfBirth;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "participant")
    private Set<TournamentParticipant> tournamentParticipant = new HashSet<>();


}
