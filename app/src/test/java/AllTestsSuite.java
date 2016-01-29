import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import accumulate_calls.BufferSamplesTest;
import await.AwaitSamplesTest;
import rx_android.RxHelperTest;
import simple.BlockingObservableTest;
import simple.NonTerminatingObservableTest;
import simple.TerminatingObservableTest;
import thead.ThreadSamplesTest;

/**
 * Runs all unit tests
 */
@RunWith (Suite.class)
@Suite.SuiteClasses({
		AwaitSamplesTest.class,
		BufferSamplesTest.class,
		RxHelperTest.class,
		BlockingObservableTest.class,
		NonTerminatingObservableTest.class,
		TerminatingObservableTest.class,
		ThreadSamplesTest.class
})
public class AllTestsSuite {
}