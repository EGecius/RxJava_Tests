package await;

import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;

/**
 * Sample code to perform {@link TestSubscriber} await assertions on.
 */
public class AwaitSamples {

	/** Returns Observable which does not call either onComplete or onError */
	public Observable<String> getNonTerminatingObservable() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				subscriber.onNext("Test");
				// onComplete() or onError() not called intentionally
			}
		});
	}
}
