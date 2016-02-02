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

	@Test (expected = AssertionError.class)
	public void when_nonTerminatingObservableSubscribedTo_thenAwaitAssertionFailsAfterItExpires() {
		//WHEN
		samples.getNonTerminatingObservable().subscribe(testSubscriber);
		//THEN
		waitedPassedDelayTime();
		testSubscriber.assertCompleted();
	}

	@Test
	public void when_delayedObservableCalled_thenSubscriberNotCompletedIfCalledImmediately() {
		//WHEN
		observableSubscribedTo();
		//THEN
		testSubscriber.assertNotCompleted();
	}

	@Test
	public void when_observableSubscribedTo_and_waitedPassedDelayTime_then_subscriberCompleted() {
		//WHEN
		observableSubscribedTo();
		//AND
		waitedPassedDelayTime();
		//THEN
		testSubscriber.assertCompleted();
	}

	private void observableSubscribedTo() {
		samples.getDelayedObservable().subscribe(testSubscriber);
	}

	private void waitedPassedDelayTime() {
		testSubscriber.awaitTerminalEvent(AwaitSamples.DELAY_MS + 10, TimeUnit.MILLISECONDS);
	}

}