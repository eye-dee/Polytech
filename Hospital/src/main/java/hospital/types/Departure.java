package hospital.types;

import lombok.Data;
import lombok.ToString;

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
public class Departure {
    private static final long serialVersionUID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
