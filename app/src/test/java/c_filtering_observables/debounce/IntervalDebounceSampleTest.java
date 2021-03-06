package c_filtering_observables.debounce;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

/**
 * Single Responsibility:
 *
 * Tests for {@link IntervalDebounceSample}
 */
public class IntervalDebounceSampleTest {

	IntervalDebounceSample sample;
	TestSubscriber<Long> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		sample = new IntervalDebounceSample();
	}

	@Test
	public void when_subscribedToIntervalDebounceObservable_then_noNotificationsFoundImmediately() {
		//WHEN
		subscribedToIntervalDebounceObservable();
		//THEN
		noNotificationsFoundImmediately();
	}

	private void subscribedToIntervalDebounceObservable() {
		sample.getObservable().subscribe(testSubscriber);
	}

	private void noNotificationsFoundImmediately() {
		testSubscriber.assertNoValues();
		testSubscriber.assertNoTerminalEvent();
	}

	@Test
	public void	when_subscribedToIntervalDebounceObservable_and_waitedPassedIntervalAndDebounceTime_then_singleEmissionFound() {
		//WHEN
		subscribedToIntervalDebounceObservable();
		//AND
		waitedPassedIntervalAndDebounceTime();
		//THEN
		singleEmissionFound();
	}
	@Test
	public void
	when_subscribedToIntervalDebounceObservable_and_waitedPassedTwiceIntervalTimeAndDebounceTime_then_twoEmissionsFound
			() {
		//WHEN
		subscribedToIntervalDebounceObservable();
		//AND
		waitedPassedTwiceIntervalTimeAndDebounceTime();
		//THEN
		twoEmissionsFound();
	}

	private void twoEmissionsFound() {
		testSubscriber.assertValueCount(2);
	}

	private void waitedPassedTwiceIntervalTimeAndDebounceTime() {
		int debounceMs = 2 * IntervalDebounceSample.INTERVAL_MS + IntervalDebounceSample.DEBOUNCE_MS + 10;
		testSubscriber.awaitTerminalEvent(debounceMs, TimeUnit.MILLISECONDS);
	}

	private void waitedPassedIntervalAndDebounceTime() {
		int debounceMs = IntervalDebounceSample.INTERVAL_MS + IntervalDebounceSample.DEBOUNCE_MS + 10;
		testSubscriber.awaitTerminalEvent(debounceMs, TimeUnit.MILLISECONDS);
	}

	private void singleEmissionFound() {
		testSubscriber.assertValueCount(1);
	}

}