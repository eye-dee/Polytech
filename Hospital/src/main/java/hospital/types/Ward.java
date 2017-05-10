package hospital.types;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Data
@Entity
@Table(name = "WARDS")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ward {
    private static final long serialVersionUID = -1798070786993154676L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WardIdSequence")
    @SequenceGenerator(name="WardIdSequence", sequenceName="WardIdSequence")
    @Column(name = "wardId")
    private Long wardId;

    @Column(name = "wardName")
    private String wardName;

    @Column(name = "departureId")
    private Long departureId;

    @Column(name = "maxCount")
    private Long maxCount;

    @Transient
    private List<People> peoples = new ArrayList<>();

    public void addPeople(final People people) {
        peoples.add(people);
    }
}
