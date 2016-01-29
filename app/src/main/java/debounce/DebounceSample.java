package debounce;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Sample for to run on tests related to {@link Observable#debounce(long, TimeUnit)}
 */
public class DebounceSample {

	public static final int DEBOUNCE_MS = 400;
	public static final String ON_NEXT_STRING = "test";

	public Observable<String> getDebounceObservable() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				subscriber.onNext(ON_NEXT_STRING);
				//not calling onComplete() or onError() so that debounce actually waits before emitting
			}
		}).debounce(DEBOUNCE_MS, TimeUnit.MILLISECONDS);
	}


}
