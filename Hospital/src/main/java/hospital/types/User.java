package hospital.types;

import lombok.*;

import javax.persistence.*;

/**
 * Polytech
 * Created by igor on 03.05.17.
 */

@ToString
@EqualsAndHashCode
@Data
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERSTABLE")
public class User {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "pass")
    private String password;
}
