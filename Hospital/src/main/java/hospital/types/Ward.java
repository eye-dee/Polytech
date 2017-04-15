package hospital.types;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */

@ToString
@Data
@Entity
@Table(name = "WARDS")
public class Ward {
    private static final long serialVersionUID = -1798070786993154676L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "wardId")
    private Long wardId;

    @Column(name = "wardName")
    private String wardName;

    @Column(name = "departureId")
    private Long departureId;

    @Transient
    private List<People> peoples = new ArrayList<>();

    public void addPeople(final People people) {
        peoples.add(people);
    }
}
