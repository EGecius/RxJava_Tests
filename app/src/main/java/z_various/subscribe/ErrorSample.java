package z_various.subscribe;

import rx.Observable;
import rx.Subscriber;

/**
 * Allows testing handling errors
 */
final class ErrorSample {

	Observable<String> wilNotThrowError() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(final Subscriber<? super String> subscriber) {
				subscriber.onNext("one");
				subscriber.onNext("two");
			}
		});
	}

	Observable<String> willThrowError() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(final Subscriber<? super String> subscriber) {
				subscriber.onNext("one");
				subscriber.onNext("two");
				subscriber.onError(new Exception());
			}
		});
	}
}
