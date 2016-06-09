package c_filtering_observables.debounce;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Single Responsibility:
 *
 * Tests for {@link DebounceSample}
 */
public class DebounceSampleTest {

	DebounceSample sample;
	TestSubscriber<String> testSubscriber = new TestSubscriber<>();

	@Before
	public void setup() {
		sample = new DebounceSample();
	}

	@Test
	public void when_subscribedToDebounceObservable_then_noNotificationsReceivedImmediately() {
		//WHEN
		subscribedToDebounceObservable();
		//THEN
		noNotificationsReceivedImmediately();
	}

	private void subscribedToDebounceObservable() {
		sample.getDebounceObservable().subscribe(testSubscriber);
	}

	private void noNotificationsReceivedImmediately() {
		testSubscriber.assertNoValues();
		testSubscriber.assertNoTerminalEvent();
	}

	@Test
	public void when_subscribedToDebounceObservable_and_waitedPassedDebounceTime_then_singleStringEmitted() {
		//WHEN
		subscribedToDebounceObservable();
			//AND
		waitedPassedDebounceTime();
		//THEN
		singleStringEmitted();
	}

	private void waitedPassedDebounceTime() {
		testSubscriber.awaitTerminalEvent(DebounceSample.DEBOUNCE_MS + 10, TimeUnit.MILLISECONDS);
	}

	private void singleStringEmitted() {
		testSubscriber.assertValueCount(1);
		String onNext = testSubscriber.getOnNextEvents().get(0);
		assertThat(onNext).isEqualTo(DebounceSample.ON_NEXT_STRING);
	}

	@Test
	public void when_subscribedToDebounceObservable_and_waitedPassedDoubleTheDebounceTime_then_singleStringEmitted() {
		//WHEN
		subscribedToDebounceObservable();
		//AND
		waitedPassedDoubleTheDebounceTime();
		//THEN
		singleStringEmitted();
	}

	private void waitedPassedDoubleTheDebounceTime() {
		int multiplier = 2;
		testSubscriber.awaitTerminalEvent(multiplier * DebounceSample.DEBOUNCE_MS, TimeUnit.MILLISECONDS);
	}

}