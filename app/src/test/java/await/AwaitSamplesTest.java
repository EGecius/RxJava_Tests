package await;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test (expected = AssertionError.class)
	public void when_nonTerminatingObservableSubscribedTo_thenAwaitAssertionFailsAfterItExpires() {
		//WHEN
		samples.getNonTerminatingObservable().subscribe(testSubscriber);
		//THEN
		testSubscriber.awaitTerminalEvent(100, TimeUnit.MILLISECONDS);
		testSubscriber.assertCompleted();
	}

	@Test
	public void when_delayedObservableCalled_thenSubscriberNotCompletedIfCalledImmediately() {
		//WHEN
		samples.getDelayedObservable().subscribe(testSubscriber);
		//THEN
		testSubscriber.assertNotCompleted();
	}

	@Test
	public void when_delayedObservableCalled_thenSubscriberCompletedIfCalledWithAwait() {
		//WHEN
		samples.getDelayedObservable().subscribe(testSubscriber);
		testSubscriber.awaitTerminalEvent(100, TimeUnit.MILLISECONDS);
		//THEN
		testSubscriber.assertCompleted();
	}


}