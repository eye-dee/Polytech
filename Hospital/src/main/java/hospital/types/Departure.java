package hospital.types;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */

@ToString
@Data
@Entity
@Table(name = "DEPARTURES")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Departure {
    private static final long serialVersionUID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DepartureIdSequence")
    @SequenceGenerator(name="DepartureIdSequence", sequenceName="DepartureIdSequence")
    @Column(name = "departureId")
    private Long departureId;

    @Column(name = "departureName")
    private String departureName;

    @Transient
    private List<Ward> wards = new ArrayList<>();

    public void addWard(final Ward ward) {
        wards.add(ward);
    }
}
