package hospital.types;

import lombok.*;

import javax.persistence.*;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */

@ToString(exclude = {"diagnosisId","departureId"})
@Data
@Entity
@Table(name = "DIAGNOSIS")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Diagnosis {
    private static final long serialVersionUID = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DiagnosisIdSequence")
    @SequenceGenerator(name="DiagnosisIdSequence", sequenceName="DiagnosisIdSequence")
    @Column(name = "diagnosisId")
    private Long diagnosisId;

    @Column(name = "diagnosisName")
    private String diagnosisName;

    @Column(name = "departureId")
    private Long departureId;
}
