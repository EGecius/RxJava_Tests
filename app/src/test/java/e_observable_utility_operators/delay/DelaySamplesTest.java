package e_observable_utility_operators.delay;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

public class DelaySamplesTest {

	DelaySamples samples;
	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		samples = new DelaySamples();
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
	public void when_delayedObservableSubscribedTo_thenSubscriberNotCompletedIfCalledImmediately() {
		//WHEN
		delayedObservableSubscribedTo();
		//THEN
		testSubscriber.assertNotCompleted();
	}

	@Test
	public void when_observableSubscribedTo_and_waitedPassedDelayTime_then_subscriberCompleted() {
		//WHEN
		delayedObservableSubscribedTo();
		//AND
		waitedPassedDelayTime();
		//THEN
		testSubscriber.assertCompleted();
	}

	private void delayedObservableSubscribedTo() {
		samples.getDelayedObservable().subscribe(testSubscriber);
	}

	private void waitedPassedDelayTime() {
		testSubscriber.awaitTerminalEvent(DelaySamples.DELAY_MS + 10, TimeUnit.MILLISECONDS);
	}

}