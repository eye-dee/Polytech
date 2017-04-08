package jcstress;

/**
 * Created by igor on 24.03.17.
 */

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
@State
public class JCStressExampleTest {

    @Actor
    public void actor1(final IntResult2 result) {
        try {
            result.r1 = JCStressExample.isJsonObject("1234") ? 1 : 0;
        } catch(final Exception ex) {
            result.r1 = -1;
        }
    }

    @Actor
    public void actor2(final IntResult2 result) {
        try {
            result.r2 = JCStressExample.isJsonObject("5678") ? 1 : 0;
        } catch(final Exception ex) {
            result.r2 = -1;
        }
    }
}
