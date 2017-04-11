package jcstress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.openjdk.jcstress.infra.runners.TestConfig;
import org.openjdk.jcstress.infra.collectors.TestResultCollector;
import org.openjdk.jcstress.infra.runners.Runner;
import org.openjdk.jcstress.infra.runners.StateHolder;
import org.openjdk.jcstress.util.Counter;
import org.openjdk.jcstress.vm.WhiteBoxSupport;
import java.util.concurrent.ExecutionException;
import jcstress.JCStressExampleTest;
import org.openjdk.jcstress.infra.results.IntResult2_jcstress;

public class JCStressExampleTest_jcstress extends Runner<IntResult2_jcstress> {

    volatile StateHolder<JCStressExampleTest, IntResult2_jcstress> version;

    public JCStressExampleTest_jcstress(TestConfig config, TestResultCollector collector, ExecutorService pool) {
        super(config, collector, pool, "jcstress.JCStressExampleTest");
    }

    @Override
    public void sanityCheck() throws Throwable {
        sanityCheck_API();
        sanityCheck_Footprints();
    }

    private void sanityCheck_API() throws Throwable {
        final JCStressExampleTest t = new JCStressExampleTest();
        final JCStressExampleTest s = new JCStressExampleTest();
        final IntResult2_jcstress r = new IntResult2_jcstress();
        Collection<Future<?>> res = new ArrayList<>();
        res.add(pool.submit(() -> t.actor1(r)));
        res.add(pool.submit(() -> t.actor2(r)));
        for (Future<?> f : res) {
            try {
                f.get();
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        }
    }

    private void sanityCheck_Footprints() throws Throwable {
        config.adjustStrides(size -> {
            version = new StateHolder<>(new JCStressExampleTest[size], new IntResult2_jcstress[size], 2, config.spinLoopStyle);
            final JCStressExampleTest t = new JCStressExampleTest();
            for (int c = 0; c < size; c++) {
                IntResult2_jcstress r = new IntResult2_jcstress();
                JCStressExampleTest s = new JCStressExampleTest();
                version.rs[c] = r;
                version.ss[c] = s;
                s.actor1(r);
                s.actor2(r);
            }
        });
    }

    @Override
    public Counter<IntResult2_jcstress> internalRun() {
        version = new StateHolder<>(new JCStressExampleTest[0], new IntResult2_jcstress[0], 2, config.spinLoopStyle);

        control.isStopped = false;
        Collection<Future<Counter<IntResult2_jcstress>>> tasks = new ArrayList<>();
        tasks.add(pool.submit(this::actor1));
        tasks.add(pool.submit(this::actor2));

        try {
            TimeUnit.MILLISECONDS.sleep(config.time);
        } catch (InterruptedException e) {
        }

        control.isStopped = true;

        waitFor(tasks);

        Counter<IntResult2_jcstress> counter = new Counter<>();
        for (Future<Counter<IntResult2_jcstress>> f : tasks) {
            try {
                counter.merge(f.get());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        return counter;
    }

    public final void jcstress_consume(StateHolder<JCStressExampleTest, IntResult2_jcstress> holder, Counter<IntResult2_jcstress> cnt, int a, int actors) {
        JCStressExampleTest[] ss = holder.ss;
        IntResult2_jcstress[] rs = holder.rs;
        int len = ss.length;
        int left = a * len / actors;
        int right = (a + 1) * len / actors;
        for (int c = left; c < right; c++) {
            IntResult2_jcstress r = rs[c];
            JCStressExampleTest s = ss[c];
            cnt.record(r);
            r.r1 = 0;
            r.r2 = 0;
        }
    }

    public final void jcstress_updateHolder(StateHolder<JCStressExampleTest, IntResult2_jcstress> holder) {
        if (!holder.tryStartUpdate()) return;
        JCStressExampleTest[] ss = holder.ss;
        IntResult2_jcstress[] rs = holder.rs;
        int len = ss.length;

        int newLen = holder.updateStride ? Math.max(config.minStride, Math.min(len * 2, config.maxStride)) : len;

        JCStressExampleTest[] newS = ss;
        IntResult2_jcstress[] newR = rs;
        if (newLen > len) {
            newS = Arrays.copyOf(ss, newLen);
            newR = Arrays.copyOf(rs, newLen);
            for (int c = len; c < newLen; c++) {
                newR[c] = new IntResult2_jcstress();
                newS[c] = new JCStressExampleTest();
            }
         }

        version = new StateHolder<>(control.isStopped, newS, newR, 2, config.spinLoopStyle);
        holder.finishUpdate();
   }

    public final Counter<IntResult2_jcstress> actor1() {

        Counter<IntResult2_jcstress> counter = new Counter<>();
        while (true) {
            StateHolder<JCStressExampleTest,IntResult2_jcstress> holder = version;
            if (holder.stopped) {
                return counter;
            }

            JCStressExampleTest[] ss = holder.ss;
            IntResult2_jcstress[] rs = holder.rs;
            int size = ss.length;

            holder.preRun();

            for (int c = 0; c < size; c++) {
                JCStressExampleTest s = ss[c];
                IntResult2_jcstress r = rs[c];
                r.trap = 0;
                s.actor1(r);
            }

            holder.postRun();

            jcstress_consume(holder, counter, 0, 2);
            jcstress_updateHolder(holder);

            holder.postUpdate();
        }
    }

    public final Counter<IntResult2_jcstress> actor2() {

        Counter<IntResult2_jcstress> counter = new Counter<>();
        while (true) {
            StateHolder<JCStressExampleTest,IntResult2_jcstress> holder = version;
            if (holder.stopped) {
                return counter;
            }

            JCStressExampleTest[] ss = holder.ss;
            IntResult2_jcstress[] rs = holder.rs;
            int size = ss.length;

            holder.preRun();

            for (int c = 0; c < size; c++) {
                JCStressExampleTest s = ss[c];
                IntResult2_jcstress r = rs[c];
                r.trap = 0;
                s.actor2(r);
            }

            holder.postRun();

            jcstress_consume(holder, counter, 1, 2);
            jcstress_updateHolder(holder);

            holder.postUpdate();
        }
    }

}
