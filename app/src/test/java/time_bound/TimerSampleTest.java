package time_bound;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

/**
 * Single Responsibility:
 *
 * Tests for {@link TimerSample}
 */
public class TimerSampleTest {

	TimerSample sample;

	TestSubscriber<Long> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		sample = new TimerSample();
	}

	@Test
	public void when_observableSubscribedTo_then_noEmissionsFoundImmediately() {
		//WHEN
		observableSubscribedTo();
		//THEN
		noEmissionsFoundImmediately();
	}

	@Test
	public void when_observableSubscribedTo_and_waitedPassedDelay_then_singleEmissionFound_and_completed() {
		//WHEN
		observableSubscribedTo();
			//AND
		waitedPassedDelay();
		//THEN
		singleEmissionFound();
			//AND
		completed();
	}

	private void completed() {
		testSubscriber.assertCompleted();
	}

	private void waitedPassedDelay() {
		testSubscriber.awaitTerminalEvent(TimerSample.DELAY_MS + 10, TimeUnit.MILLISECONDS);
	}

	private void singleEmissionFound() {
		testSubscriber.assertValueCount(1);
	}

	private void observableSubscribedTo() {
		sample.getObservable().subscribe(testSubscriber);
	}

	private void noEmissionsFoundImmediately() {
		testSubscriber.assertNoValues();
		testSubscriber.assertNoTerminalEvent();
	}

}