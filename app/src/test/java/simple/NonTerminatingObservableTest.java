package simple;

import org.junit.Before;
import org.junit.Test;

import activities.DaysOfTheWeekSamples;
import rx.observers.TestSubscriber;

/***
 * Tests for Observable which has emitted values but has not terminated
 */
public class NonTerminatingObservableTest {

	DaysOfTheWeekSamples samples;
	TestSubscriber<String> subscriber;

	@Before
	public void setup() {
		subscriber = new TestSubscriber<>();

		samples = new DaysOfTheWeekSamples();
		samples.getNonTerminatingDaysNamesObservable().subscribe(subscriber);
	}

	/** Since the Observable never called onError() or OnComplete() subscription is not terminated */
	@Test
	public void expectedValuesAreEmittedButSubscriptionIsNotComplete() {

		//expected values emitted
		subscriber.assertValueCount(7);
		subscriber.assertReceivedOnNext(samples.getDayNames());

		// however, it has not been completed by onError() or onComplete()
		subscriber.assertNotCompleted();
		subscriber.assertNoTerminalEvent();
		subscriber.assertNoErrors();
	}

	@Test (expected = AssertionError.class)
	public void given_ExpectedSubscriptionEnded_then_AssertionFails() {
		subscriber.assertUnsubscribed();
	}

}