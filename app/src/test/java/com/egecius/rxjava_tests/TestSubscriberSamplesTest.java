package com.egecius.rxjava_tests;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

/**
 * Tests for {@link TestSubscriberSamples}
 */
public class TestSubscriberSamplesTest {

	TestSubscriberSamples samples;
	TestSubscriber<String> subscriber;

	@Before
	public void setup() {
		samples = new TestSubscriberSamples();
		subscriber = new TestSubscriber<>();
	}

	@Test
	public void when_subscribedCalled_thenObservableCompletesSuccessfully() {
		//WHEN
		samples.getDayNamesObservable().subscribe(subscriber);

		//no  errors have occurred
		subscriber.assertNoErrors();
		// either onError() or onComplete() was called
		subscriber.assertCompleted();
		//since either onError() or onComplete() was called, nothing else to emmit. Subscriber has been unsubscribed
		subscriber.assertUnsubscribed();
	}

	@Test
	public void when_subscribedCalled_thenItEmitsExpectedValues() {
		//WHEN
		samples.getDayNamesObservable().subscribe(subscriber);

		//THEN it emits the expected values
		subscriber.assertReceivedOnNext(samples.getDayNames());
	}
}