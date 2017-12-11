package pl.aaugustyniak.sequencing;

import com.google.code.tempusfugit.concurrency.annotations.GuardedBy;
import com.google.code.tempusfugit.concurrency.annotations.ThreadSafe;
import com.google.common.base.Optional;
import pl.aaugustyniak.sequencing.rule.LPTRule;
import pl.aaugustyniak.sequencing.rule.SPTRule;


/**
 * @author aaugustyniak
 */
@ThreadSafe
public class SequencingRules {


    @GuardedBy(lock = GuardedBy.Type.THIS)
    private int seq = 0;


    public synchronized int getNext() {
        return seq++;
    }


    public Optional anticipateAbsenceWithGuava() {
        return Optional.of(5);
    }

    public static void main(String[] args) {


        SequencingRules app = new SequencingRules();
        for (int i = 0; i < 10; i++) {
            System.out.println(app.getNext());
        }
        LPTRule lptr = new LPTRule();
        lptr.exec();

        SPTRule sptr = new SPTRule();
        sptr.exec();


    }
}
