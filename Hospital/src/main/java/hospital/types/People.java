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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PEOPLEIDSEQUENCE")
    @SequenceGenerator(name="PEOPLEIDSEQUENCE", sequenceName="PEOPLEIDSEQUENCE")
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
