package hospital.types;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */

@ToString(exclude = {"diagnosisId","departureId"})
@Data
@Entity
@Table(name = "DIAGNOSIS")
public class Diagnosis {
    private static final long serialVersionUID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "diagnosisId")
    private Long diagnosisId;

    @Column(name = "diagnosisName")
    private String diagnosisName;

    @Column(name = "departureId")
    private Long departureId;
}
