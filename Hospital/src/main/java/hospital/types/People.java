package hospital.types;

import lombok.*;

import javax.persistence.*;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */

@ToString(exclude = {"peopleId", "diagnosisId","wardId"})
@EqualsAndHashCode
@Data
@AllArgsConstructor
@Entity
@Builder
@Table(name = "PEOPLE")
public class People {
    public People() {
    }

    private static final long serialVersionUID = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "peopleId")
    private Long peopleId;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "fatherName")
    private String fatherName;

    @Column(name = "diagnosisId")
    private Long diagnosisId;
    @Column(name = "wardId")
    private Long wardId;

    @Transient
    private Diagnosis diagnosis;

}
