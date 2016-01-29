package await;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

/**
 * Single Responsibility:
 *
 * Tests for {@link AwaitSamples}
 */
public class AwaitSamplesTest {

	AwaitSamples samples;
	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		samples = new AwaitSamples();
	}

	@Test
	public void when_nonTerminatingObservableSubscribedTo_thenAwaitAssertionFailsAfterItExpires() {
		//WHEN
		samples.getNonTerminatingObservable().subscribe(testSubscriber);
		//THEN
		testSubscriber.awaitTerminalEvent(100, TimeUnit.MILLISECONDS);
		testSubscriber.assertCompleted();
	}

}