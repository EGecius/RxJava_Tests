package simple;

import org.junit.Before;
import org.junit.Test;

import activities.DaysOfTheWeekSamples;
import rx.Subscription;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;


/***
 * Tests for Observable which has emitted values but has not terminated
 */
public class NonTerminatingObservableTest {

	DaysOfTheWeekSamples samples;
	TestSubscriber<String> subscriber;
	private Subscription subscription;

	@Before
	public void setup() {
		subscriber = new TestSubscriber<>();

		samples = new DaysOfTheWeekSamples();
		subscription = samples.getNonTerminatingDaysNamesObservable().subscribe(subscriber);
	}

	/** Since the Observable never called onError() or OnComplete() subscription is not terminated */
	@Test
	public void expectedValuesAreEmittedButSubscriptionIsNotComplete() {

		//expected values emitted
		subscriber.assertValueCount(7);
		subscriber.assertReceivedOnNext(samples.getDayNames());
		assertThat(subscriber.getOnNextEvents().size()).isEqualTo(7);

		// however, it has not been completed by onError() or onComplete()
		subscriber.assertNotCompleted();
		subscriber.assertNoTerminalEvent();
		subscriber.assertNoErrors();
		assertThat(subscriber.getOnCompletedEvents().size()).isEqualTo(0);
		assertThat(subscriber.getOnErrorEvents().size()).isEqualTo(0);
	}

	@Test (expected = AssertionError.class)
	public void given_ExpectedSubscriptionEnded_then_AssertionFails() {
		subscriber.assertUnsubscribed();
	}

	@Test
	public void given_expectedSubscriptionEnded_whenUnsubscribeCalled_then_AssertionSucceeds() {
		subscription.unsubscribe();
		subscriber.assertUnsubscribed();
	}

}