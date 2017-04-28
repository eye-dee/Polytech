package technopolis.greping.recursicve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ResultObject implements Serializable {
    private long stringNumber;
    private String fileName;

    @Override
    public String toString() {
        return "line #" + stringNumber +
                " " +  fileName;
    }
}
