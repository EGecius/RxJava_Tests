package z_various.subscriber;

import rx.Observable;
import rx.Subscriber;

/**
 * //todo
 */
final class SubscriberSample {

	public Observable<String> getNonCompletingObservable() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(final Subscriber<? super String> subscriber) {
				//we do not want it to complete here
			}
		});
	}

}
