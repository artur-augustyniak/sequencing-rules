package pl.aaugustyniak.sequencing;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import com.google.common.base.Optional;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * @author aaugustyniak
 */
@RunWith(ConcurrentTestRunner.class)
public class SequencingRulesTest {

    private static final int CYCLES = 100;
    private static final int THREADS = 5;
    /**
     * Expected  Sn = (a1 + an/2) * n
     */
    private static final int EXPECTED_PROGRESSION_SUM = (int) (
            ((CYCLES * THREADS - 1) / 2.0f) * CYCLES * THREADS
    );

    @Rule
    public ConcurrentRule rule = new ConcurrentRule();
    @Rule
    public RepeatingRule repeatedly = new RepeatingRule();
    private static CountDownLatch cdl;
    private static AtomicInteger progressionSum;

    private static SequencingRules app;

    @BeforeClass
    public static void beforeClass() {
        app = new SequencingRules();
        cdl = new CountDownLatch(CYCLES * THREADS);
        progressionSum = new AtomicInteger();
    }


    @Test
    @Concurrent(count = THREADS)
    @Repeating(repetition = CYCLES)
    public void processSomeSequencesConcurrently() {
        try {
            progressionSum.addAndGet(app.getNext());
        } finally {
            cdl.countDown();

        }
    }

    @Test
    @Concurrent(count = 1)
    @Repeating(repetition = 1)
    public void waitForTestEndAndAssert() throws InterruptedException {
        cdl.await();
        System.out.println("Expected sum: " + EXPECTED_PROGRESSION_SUM);
        assertEquals(EXPECTED_PROGRESSION_SUM, progressionSum.get());
    }


    @Test
    public void testAbsenceAnticipation() {
        Optional op = app.anticipateAbsenceWithGuava();
        assertEquals(5, op.get());
    }


}
