package z_various.subscriber;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.Subscriber;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SubscriberSample}
 */
@RunWith (MockitoJUnitRunner.class)
public class SubscriberSampleTest {

	@InjectMocks SubscriberSample sample;

	//Subscriber implements both Observer & Subscription interfaces, so you can unsubscribe on it directly
	@Test
	public void subscriberAlllowsUnsisbscribing() {
		//WHEN
		Observable<String> observable = sample.getNonCompletingObservable();
		//THEN

		Subscriber<String> subscriber = new Subscriber<String>() {
			@Override
			public void onCompleted() {}

			@Override
			public void onError(final Throwable e) {}

			@Override
			public void onNext(final String s) {}
		};
		observable.subscribe(subscriber);

		// since Observable has not completed, subscriber is still subscribed
		assertThat(subscriber.isUnsubscribed()).isFalse();

		subscriber.unsubscribe();
		//now that we have just unsubscribed, we can assert that on subscriber
		assertThat(subscriber.isUnsubscribed()).isTrue();
	}

}